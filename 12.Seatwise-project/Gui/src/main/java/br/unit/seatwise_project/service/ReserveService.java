
package br.unit.seatwise_project.service;

import br.unit.seatwise_project.config.ApiConfig;
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


public class ReserveService {


    // Attributes

    private final static Logger logger = LoggerUtils.getLogger(ReserveService.class);


    // Main methods

    public static CompletableFuture<List<Reserve>> getAllReserves() {
        logger.info("Iniciando requisição Api GET para: " + ApiConfig.RESERVE_ENDPOINT);

        HttpClient  client   = HttpClient.newHttpClient();
        HttpRequest request  = HttpRequest.newBuilder().GET().uri(URI.create(ApiConfig.RESERVE_ENDPOINT)).build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.thenApply(HttpResponse::statusCode).join();
        logger.info("Status Code da requisição: " + statusCode);

        return response
                .thenApply(HttpResponse::body)
                .thenApply(JsonUtils::parseReserves)
                .exceptionally(exception -> {
                    logger.log(Level.SEVERE,"Erro na requisição ao obter reservas", exception);
                    return null;
                }
        );
    }

    public static CompletableFuture<String> addReserve(Reserve reserve) {
        logger.info("Iniciando requisição Api POST para: " + ApiConfig.RESERVE_ENDPOINT);

        HttpClient  client   = HttpClient.newHttpClient();
        HttpRequest request  = HttpRequest.newBuilder()
                .uri(URI.create(ApiConfig.RESERVE_ENDPOINT))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtils.parseString(reserve)))
                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.thenApply(HttpResponse::statusCode).join();
        logger.info("Status Code da requisição: " + statusCode);

        return response
                .thenApply(HttpResponse::body)
                .exceptionally(exception -> {
                    logger.log(Level.SEVERE,"Erro na requisição ao obter criar reserva", exception);
                    return null;
                }
        );
    }

    public static CompletableFuture<String> deleteReserve(Long id) {
        logger.info(String.format("Iniciando requisição Api DELETE para: %s/%d", ApiConfig.RESERVE_ENDPOINT, id));

        HttpClient  client  = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(String.format("%s/%d", ApiConfig.RESERVE_ENDPOINT, id)))
                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.thenApply(HttpResponse::statusCode).join();
        logger.info("Status Code da requisição: " + statusCode);

        return response
                .thenApply(HttpResponse::body)
                .exceptionally(exception -> {
                    logger.log(Level.SEVERE, "Erro na requisição ao remover reserva", exception);
                    return null;
                }
        );
    }
}
