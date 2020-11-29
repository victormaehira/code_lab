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
import br.com.certisign.certisigner.intermv.sign.JwtTasy;

/**
 * Azure Functions with HTTP Trigger.
 */
public class SessaoNuvem {
    /**
     * This function listens at endpoint "/api/SessaoNuvem". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/SessaoNuvem
     * 2. curl {your host}/api/SessaoNuvem?name=HTTP%20Query
     */
    @FunctionName("sessaoNuvem")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info(">>sessaoNuvem");

        String json = request.getBody().get();
        context.getLogger().info("sessaoNuvem json = " + json);
        
        String urlRemoteID = System.getenv("urlRemoteID");
		JSONObject jsonReturn = new JSONObject();
		try {
			JSONObject parameter = new JSONObject(json);

			String jwt = JwtTasy.createJwt(parameter.getString("kpName"),
					parameter.getString("kpPassword").toCharArray(), parameter.getString("organization"),
					parameter.getString("system"), context);

			context.getLogger().info("sessaoNuvem jwt = " + jwt);
			String[] token = ExecuteApiRemoteIdNuvem.createTokenRemoteIdNuvem(jwt, urlRemoteID);
			jsonReturn.put("token", token[0]);
			jsonReturn.put("expiracao", token[1]);
			jsonReturn.put("status", "OK");

		} catch (Exception e) {
			context.getLogger().info("Erro ao criar sessao!" + e);
			jsonReturn = RetornoErro(803, "Erro ao criar sessao!", e);
		}

		context.getLogger().info("sessaoNuvem retorno = " + jsonReturn.toString());
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
