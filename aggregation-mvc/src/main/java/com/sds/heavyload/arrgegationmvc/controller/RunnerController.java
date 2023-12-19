package com.sds.heavyload.arrgegationmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class RunnerController {

    @Value("${url.amadeus}")
    private String amadeusUrl;

    private final RestTemplate restTemplate;

    public RunnerController(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/shoot")
    ResponseEntity<String> getSomething() {
        final var mockResult = restTemplate.getForObject(amadeusUrl+"/delay", String.class);
        return ResponseEntity.ok(mockResult);
    }
}
