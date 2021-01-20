package com.peterholub.constructorredirectscreator.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterholub.constructorredirectscreator.model.Redirect;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

@Component
@Log4j2
public class ConstructorClient {
    public static final String REDIRECT_RULES_URI = "https://ac.cnstrc.com/v1/redirect_rules?key=";
    @Value("${key}")
    private String key;
    @Value("${username}")
    private String username;

    private final ObjectMapper objectMapper;

    @Autowired
    public ConstructorClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public void createRedirect(Redirect redirect) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(redirect);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(REDIRECT_RULES_URI + key))
                .header("Content-Type", "application/json")
                .header("Authorization", basicAuth())
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e);
        }

        log.info(response.statusCode());

        log.info(response.body());

    }

    private String basicAuth() {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + "").getBytes());
    }
}
