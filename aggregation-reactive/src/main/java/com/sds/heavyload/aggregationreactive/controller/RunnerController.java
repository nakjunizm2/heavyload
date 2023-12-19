package com.sds.heavyload.aggregationreactive.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class RunnerController {

    @Value("${url.amadeus}")
    private String amadeusUrl;

    private final WebClient webClient;

    public RunnerController(final WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/shoot")
    Mono<String> getSomething() {
        return webClient.get()
                .uri(amadeusUrl+"/delay")
                .retrieve()
                .bodyToMono(String.class);
    }
}
