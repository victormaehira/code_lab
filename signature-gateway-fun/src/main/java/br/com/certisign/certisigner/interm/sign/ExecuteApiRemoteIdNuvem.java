package br.com.certisign.certisigner.interm.sign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import br.com.certisign.certisigner.intermv.SessionParameterCloud;
import br.com.certisign.certisigner.intermv.sign.SignUtil;
import br.com.certisign.servicecertimed.webservicesmv.CertimedException;

public class ExecuteApiRemoteIdNuvem {

	
	public static String[] createTokenRemoteIdNuvem(String jwt, String urlRemoteID) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("declaracao", jwt);
			
			JSONObject jsonToken = new JSONObject(postMessageWithHost(urlRemoteID, "/credential/acesso/novo", json.toString()));
			if (jsonToken.has("tokenAcesso")) {
				return new String[] { jsonToken.getString("tokenAcesso"), jsonToken.getString("expiracao") };
			}
			else {
				throw new CertimedException("Nao foi possivel criar o token.", 789);			
			}
		} catch (Exception e) {
			throw SignUtil.TrataDisparoErro(e, e.getMessage(), 804);
		}
	}
	
	public static JSONObject repositoryRemoteIdNuvem(String json, String urlRemoteID) throws Exception {
		try {
			JSONObject repositorios = new JSONObject(postMessageWithHost(urlRemoteID, "/api/user/repository", json));
			
			if (!repositorios.get("message").equals(null))
				throw new CertimedException(repositorios.getString("message"), 792);
			
			if (repositorios.get("repositories").equals(null))
				throw new CertimedException("Nenhum certificado encontrado no repositorio", 873);
			
			int ultimoCertificadoCadastrado = repositorios.getJSONArray("repositories").length() - 1;					
			JSONObject repositorio = repositorios.getJSONArray("repositories").getJSONObject(ultimoCertificadoCadastrado);
			JSONObject retorno = new JSONObject();

								
				retorno.put("description", repositorio.getString("description"));
				retorno.put("serialNumber", repositorio.getString("serialNumber"));
				retorno.put("issuer", repositorio.getString("issuer"));
				retorno.put("base64", repositorio.getString("base64").replace("\n", ""));
				retorno.put("otp", repositorios.getJSONObject("authentication").getBoolean("otp"));

			return retorno;
		} catch (Exception e) {
			throw SignUtil.TrataDisparoErro(e, e.getMessage(), 807);
		}
	}
	
	public static JSONObject aberturaSessaoRemoteIdNuvem(String jwt, String urlRemoteID, SessionParameterCloud parameter) throws Exception {
		try {			
			JSONObject json = new JSONObject();
			json.put("tokenAcesso", jwt);
			json.put("systemId", parameter.getIdSistema());
			json.put("cpf", parameter.getCpf());
			json.put("pin", parameter.getPin());
			json.put("otp", parameter.getOtp());
			JSONArray repositorio = repositoryRemoteIdNuvemCertificates(json.toString(), urlRemoteID);
			
			JSONObject jsonToken = new JSONObject();
			
			for (int i = 0; i < repositorio.length(); i++) {
				
				JSONObject certificado = (JSONObject) repositorio.get(i);
				
				json.put("issue", certificado.getString("issuer"));
				json.put("serialNumber", certificado.getString("serialNumber"));
				
				
				jsonToken = new JSONObject(postMessageWithHost(urlRemoteID, "/api/signature/tokensessao", json.toString()));
				if(jsonToken.getBoolean("status")) {
					return jsonToken;
				}
			}
			return jsonToken;
			
		} catch (Exception e) {
			throw SignUtil.TrataDisparoErro(e, e.getMessage(), 704);
		}
	}
	
	public static JSONArray repositoryRemoteIdNuvemCertificates(String json, String urlRemoteID) throws Exception {
		try {

			JSONObject repositorios = new JSONObject(postMessageWithHost(urlRemoteID, "/api/user/repository", json));

			if (!repositorios.get("message").equals(null))
				throw new CertimedException(repositorios.getString("message"), 792);
			
			if (repositorios.get("repositories").equals(null))
				throw new CertimedException("Nenhum certificado encontrado no repositorio", 873);

			JSONObject retorno = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			for (int i = 0; i < repositorios.getJSONArray("repositories").length(); i++) {
								
				JSONObject repositorio = repositorios.getJSONArray("repositories").getJSONObject(i);
				
				retorno = new JSONObject();
				retorno.put("description", repositorio.getString("description"));
				retorno.put("serialNumber", repositorio.getString("serialNumber"));
				retorno.put("issuer", repositorio.getString("issuer"));
				retorno.put("base64", repositorio.getString("base64").replace("\n", ""));
				retorno.put("otp", repositorios.getJSONObject("authentication").getBoolean("otp"));
				jsonArray.put(retorno);

			}
			return jsonArray;
		} catch (Exception e) {
			throw SignUtil.TrataDisparoErro(e, e.getMessage(), 807);
		}
	}

	
	private static String postMessageWithHost(String host, String url, String json) throws CertimedException, IOException {
		String retorno = "";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		try {
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
			throw SignUtil.TrataDisparoErro(e, e.getMessage(), 804);
		} finally {
			httpClient.close();
		}
		return retorno;
	}
	
	public static JSONObject fechamentoSessaoRemoteIdNuvem(String jwt, String urlRemoteID, SessionParameterCloud parameter) throws Exception {
		try {
			
			JSONObject json = new JSONObject();
			json.put("tokenAcesso", jwt);
			json.put("token", parameter.getTokenSessao());
			
			
			JSONObject jsonToken = new JSONObject(postMessageWithHost(urlRemoteID, "/api/signature/closeSession", json.toString()));
			return jsonToken;

		}catch (Exception e) {
			throw SignUtil.TrataDisparoErro(e, e.getMessage(), 705);
		}
	}
		
}
