package com.victor.kafka.embedded.services;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SinaisVitaisService {
	@Value(value = "${eglicemia.api.url}")
	private String eglicemiaApiUrl;
	
	@Value(value = "${eglicemia.api.url.keys}")
	private String keys;
	
	@Value(value = "${eglicemia.api.url.username}")
	private String username;
	
	@Value(value = "${eglicemia.api.url.password}")
	private String password;
	
	@Value(value = "${eglicemia.api.url.client}")
	private String clientOrigin;

	public String getEglicemiaApiUrl() {
		return eglicemiaApiUrl;
	}

	public void setEglicemiaApiUrl(String eglicemiaApiUrl) {
		this.eglicemiaApiUrl = eglicemiaApiUrl;
	}
	
	 public void post(String json) throws ClientProtocolException, IOException, InterruptedException {
	    	System.out.println(">post");
	    	CloseableHttpClient client = HttpClients.createDefault();
	    	HttpPost httpPost = new HttpPost(eglicemiaApiUrl + "/lancamentos");
	    	
	    	HashMap<String, String> parameters = new HashMap<>();
	       	parameters.put("grant_type", "password");
	    	parameters.put("client", clientOrigin);
	    	parameters.put("username", username);
	    	parameters.put("password", password);
	    	
	    	String form = parameters.keySet().stream()
	    	        .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
	    	        .collect(Collectors.joining("&"));

	    	String encoding = Base64.getEncoder().encodeToString(keys.getBytes());
	    	HttpClient clientX = HttpClient.newHttpClient();

	    	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(eglicemiaApiUrl + "/oauth/token"))
	    	        .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic "+encoding)
	    	        .POST(BodyPublishers.ofString(form)).build();
	    	HttpResponse<?> responsex = clientX.send(request, BodyHandlers.ofString());
	    	System.out.println(responsex.statusCode() + responsex.body().toString());
	    	
	 
	    	JSONObject jsonObject = new JSONObject(responsex.body().toString());
	    	String accessToken = jsonObject.getString("access_token");
	    	System.out.println("acesss_token = " + accessToken);
	    
	        StringEntity entity = new StringEntity(json);
	        httpPost.setEntity(entity);
	        httpPost.setHeader("Accept", "application/json");
	        httpPost.setHeader("Content-type", "application/json");
	        httpPost.setHeader("Authorization", "Bearer " + accessToken);

	        CloseableHttpResponse response = client.execute(httpPost);
	        System.out.println("response.getStatusLine().getStatusCode() = " + response.getStatusLine().getStatusCode());
	        client.close();
	    }


}
