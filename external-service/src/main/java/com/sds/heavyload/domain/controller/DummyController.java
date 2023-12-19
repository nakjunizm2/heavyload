package com.sds.heavyload.domain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RestController
@RequestMapping("/api/v1")
public class DummyController {

    @Value("${delay}")
    private int delay;

    @GetMapping("/delay")
    Mono<String> dummyApi() {
        return Mono.just("delayed response")
                .delayElement(Duration.ofSeconds(delay));
    }
}
