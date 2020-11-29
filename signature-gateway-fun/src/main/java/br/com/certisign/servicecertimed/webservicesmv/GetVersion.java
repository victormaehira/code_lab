package br.com.certisign.servicecertimed.webservicesmv;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetVersion {
    /**
     * This function listens at endpoint "/api/GetVersion". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/GetVersion
     * 2. curl {your host}/api/GetVersion?name=HTTP%20Query
     */
    @FunctionName("/version")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info(">getVersion");
        
        String buildVersion = System.getenv("VERSION");

        String retorno = "Mensagem: Api inicializada. Version: " + buildVersion;

        context.getLogger().info("<getVersion");
        
        return request.createResponseBuilder(HttpStatus.OK).body(retorno).build();
    }
}
