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
public class FechamentoSessaoRemoteIdCloud {
    /**
     * This function listens at endpoint "/api/FechamentoSessaoRemoteIdCloud". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/FechamentoSessaoRemoteIdCloud
     * 2. curl {your host}/api/FechamentoSessaoRemoteIdCloud?name=HTTP%20Query
     */
    @FunctionName("fechamentoSessaoRemoteIdCloud")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info(">>fechamentoSessaoRemoteIdCloud");
        
		JSONObject jsonReturns = new JSONObject();
		String json = request.getBody().get();
		String urlRemoteID = System.getenv("urlRemoteID");
		try {

			SessionParameterCloud parameter = new SessionParameterCloud();
			JSONObject req = new JSONObject(json).getJSONObject("parametros");

			parameter.setTokenSessao(req.getString("tokenSessao"));

			JSONObject retorno = ExecuteApiRemoteIdNuvem.fechamentoSessaoRemoteIdNuvem(req.getString("tokenAcesso"),
					urlRemoteID, parameter);

			// jsonReturns.put("status",retorno.getBoolean("status"));
			jsonReturns.put("message", retorno.getString("message"));

		} catch (Exception e) {
			context.getLogger().info("Erro ao realizar abertura de sessao! " + e);
			jsonReturns = RetornoErro(301, "Erro ao realizar abertura de sessao!", e);
		}
		context.getLogger().info("<<fechamentoSessaoRemoteIdCloud");
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
