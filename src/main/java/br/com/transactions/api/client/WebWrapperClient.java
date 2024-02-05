package br.com.transactions.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WebWrapperClient {


    public void post(String uri,
                     Object requestBody) {
        WebClient.builder().build().post()
                .uri(uri)
                .bodyValue(requestBody)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
