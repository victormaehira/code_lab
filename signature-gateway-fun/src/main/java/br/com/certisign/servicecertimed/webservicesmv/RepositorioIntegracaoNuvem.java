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
/**
 * Azure Functions with HTTP Trigger.
 */
public class RepositorioIntegracaoNuvem {
    /**
     * This function listens at endpoint "/api/RepositorioIntegracaoNuvem". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/RepositorioIntegracaoNuvem
     * 2. curl {your host}/api/RepositorioIntegracaoNuvem?name=HTTP%20Query
     */
    @FunctionName("repositorioIntegracaoNuvem")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info(">> repositorioIntegracaoNuvem");

        String json = request.getBody().get();
        context.getLogger().info("json = " + json);
        
        String urlRemoteID = System.getenv("urlRemoteID");
        
    	JSONObject jsonReturn = new JSONObject();
		try {
			JSONObject parameter = new JSONObject(json);
			jsonReturn = ExecuteApiRemoteIdNuvem.repositoryRemoteIdNuvem(parameter.toString(), urlRemoteID);
			jsonReturn.put("status", "OK");

		} catch (Exception e) {
			jsonReturn = RetornoErro(808, "Erro ao buscar o repositorio!", e);
		}
		context.getLogger().info("<repositoriIntegracaoNuvem ");

		return request.createResponseBuilder(HttpStatus.OK).body(jsonReturn.toString()).build();
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
