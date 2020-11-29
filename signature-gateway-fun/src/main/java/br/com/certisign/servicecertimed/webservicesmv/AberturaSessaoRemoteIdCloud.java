package br.com.certisign.servicecertimed.webservicesmv;

import java.io.StringWriter;
import java.util.Optional;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import br.com.certisign.certisigner.interm.sign.ExecuteApiRemoteIdNuvem;
import br.com.certisign.certisigner.intermv.SessionParameterCloud;

/**
 * Azure Functions with HTTP Trigger.
 */
public class AberturaSessaoRemoteIdCloud {
    /**
     * This function listens at endpoint "/api/AberturaSessaoRemoteIdCloud". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/AberturaSessaoRemoteIdCloud
     * 2. curl {your host}/api/AberturaSessaoRemoteIdCloud?name=HTTP%20Query
     */
    @FunctionName("aberturaSessaoRemoteIdCloud")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {  
        context.getLogger().info(">aberturaSessaoRemoteIdCloud");
		JSONObject jsonReturns = new JSONObject();

		String urlRemoteID = System.getenv("urlRemoteID");
		
		try {

	        String json = request.getBody().get();
			SessionParameterCloud parameter = new SessionParameterCloud();
			JSONObject req = new JSONObject(json).getJSONObject("parametros");

			parameter.setCpf(req.getString("cpf"));
			parameter.setPin(req.getString("pin"));
			parameter.setIdSistema(req.getString("systemId"));
			parameter.setIdSistema(req.getString("systemId"));
			if (req.has("otp"))
				parameter.setOtp(req.getString("otp"));

//			JSONObject retorno = ExecuteApiRemoteIdNuvem.aberturaSessaoRemoteIdNuvem(req.getString("tokenAcesso"),
//					ConfigTemporario.getInstance().getPathUrl(), parameter);

			JSONObject retorno = ExecuteApiRemoteIdNuvem.aberturaSessaoRemoteIdNuvem(req.getString("tokenAcesso"),
					urlRemoteID, parameter);

			
			jsonReturns.put("status", retorno.getBoolean("status"));
			jsonReturns.put("message", retorno.getString("message"));
			jsonReturns.put("token", retorno.getString("token"));

		} catch (Exception e) {
			context.getLogger().info("Erro ao realizar abertura de sessao!" +  e);
			jsonReturns = RetornoErro(301, "Erro ao realizar abertura de sessao!", e);
		}

		return request.createResponseBuilder(HttpStatus.OK).body(jsonReturns.toString()).build();
    }
    
    private JSONObject RetornoErro(int id, String erro, Exception e) {
		JSONObject jsonReturn = new JSONObject();

		try {
			if (e instanceof CertimedException) {
				CertimedException excessao = (CertimedException) e;
				jsonReturn.put("id", excessao.getId());
				jsonReturn.put("status", "Erro " + excessao.getId());
				jsonReturn.put("message", StringEscapeUtils.unescapeHtml4(excessao.getMessage().replace("amp;", "")));
			} else if (e instanceof JSONException) {
				//LOGGER.error(e);
				jsonReturn.put("id", 100);
				jsonReturn.put("status", "Erro 100");
				jsonReturn.put("message", "Estrutura de envio invalida!");
			} else {
				//LOGGER.error(e);
				jsonReturn.put("id", id);
				jsonReturn.put("status", "Erro " + id);
				jsonReturn.put("message", erro);
			}

		} catch (JSONException ex) {
			//LOGGER.error(ex);
			StringWriter jsonOut = new StringWriter();
			jsonOut.write(ex.getMessage());
		}

		return jsonReturn;
	}
}
