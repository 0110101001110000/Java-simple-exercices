
package br.unit.seatwise_project.service;

import br.unit.seatwise_project.config.ApiConfig;
import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.utility.JsonUtils;
import br.unit.seatwise_project.utility.LoggerUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


public class ChairService {


    // Attributes

    private static Logger logger = LoggerUtils.getLogger(ChairService.class);


    // Main methods

    public static CompletableFuture<List<Chair>> getAllChairs() {
        logger.info("Iniciando requisição Api para: " + ApiConfig.CHAIR_ENDPOINT);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ApiConfig.CHAIR_ENDPOINT)).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return response
                .thenApply(HttpResponse::body)
                .thenApply(JsonUtils::parseChair)
                .exceptionally(exception -> {
                    logger.log(Level.SEVERE,"Erro na requisição ao obter cadeiras", exception);
                    final List<Chair> nullChairs = new ArrayList<>(1);
                    nullChairs.add(new Chair(null));
                    return nullChairs;
        });
    }
}
