package br.com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class Post {
	public String postMessageWithHost(String host, String url, String json) throws IOException {
		System.out.println(">postMessageWithHost");
		String retorno = "";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		try {
			System.out.println("host + url = " + host + url);
			HttpPost postRequest = new HttpPost(host + url);
			
			JSONObject jsonObject = new JSONObject(json);
			if (jsonObject.has("tokenAcesso")) {
				String token = jsonObject.getString("tokenAcesso");
				jsonObject.remove("tokenAcesso");
				postRequest.addHeader("Authorization", "Bearer " + token);
			}
			
			StringEntity input = new StringEntity(jsonObject.toString());
			input.setContentType("application/json");
			postRequest.setEntity(input);
			
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			while ((output = br.readLine()) != null) {
				retorno = retorno + output;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			httpClient.close();
		}
		System.out.println("<postMessageWithHost");
		return retorno;
	}
}
