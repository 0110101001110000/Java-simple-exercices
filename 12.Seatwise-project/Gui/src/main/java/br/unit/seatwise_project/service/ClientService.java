
package br.unit.seatwise_project.service;

import br.unit.seatwise_project.config.ApiConfig;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.model.Reserve;
import br.unit.seatwise_project.utility.JsonUtils;
import br.unit.seatwise_project.utility.LoggerUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


public class ClientService {


    // Attributes

    private final static Logger logger = LoggerUtils.getLogger(ClientService.class);


    // Main methods

    public static CompletableFuture<Client> getClientByEmail(String email) {
        String finalUri = String.format("%s/%s", ApiConfig.CLIENT_ENDPOINT, email);

        logger.info("Iniciando requisição Api GET para: " + finalUri);

        HttpClient  client   = HttpClient.newHttpClient();
        HttpRequest request  = HttpRequest.newBuilder().GET().uri(URI.create(finalUri)).build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.thenApply(HttpResponse::statusCode).join();
        logger.info("Status Code da requisição: " + statusCode);

        return response
                .thenApply(HttpResponse::body)
                .thenApply(JsonUtils::parseClient)
                .exceptionally(exception -> {
                    logger.log(Level.SEVERE,"Erro na requisição ao obter cliente", exception);
                    return null;
                }
        );
    }

    public static CompletableFuture<String> addClient(Client newClient) {
        logger.info("Iniciando requisição Api POST para: " + ApiConfig.CLIENT_ENDPOINT);

        HttpClient  client   = HttpClient.newHttpClient();
        HttpRequest request  = HttpRequest.newBuilder()
                .uri(URI.create(ApiConfig.CLIENT_ENDPOINT))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtils.parseString(newClient)))
                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.thenApply(HttpResponse::statusCode).join();
        logger.info("Status Code da requisição: " + statusCode);

        return response
                .thenApply(HttpResponse::body)
                .exceptionally(exception -> {
                    logger.log(Level.SEVERE,"Erro na requisição ao registrar cliente", exception);
                    return null;
                }
        );
    }
}
