package com.sds.heavyload.aggregationreactive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${url.amadeus}")
    private String amadeusUrl;
    @Value("${webclient.maxConnection}")
    private int maxConnection;
    @Value("${webclient.pendingAcquireMaxCount}")
    private int pendingAcquireMaxCount;
    @Value("${webclient.pendingAcquireTimeout}")
    private int pendingAcquireTimeout;
    @Value("${webclient.maxIdleTime}")
    private int maxIdleTime;
    @Value("${webclient.maxLifeTime}")
    private int maxLifeTime;

    @Bean
    public WebClient getWebclient(WebClient.Builder webclientBuilder){
        final var provider =
                ConnectionProvider
                        .builder("custom")
                        .maxConnections(maxConnection)
                        .pendingAcquireMaxCount(pendingAcquireMaxCount)
                        .pendingAcquireTimeout(Duration.ofSeconds(pendingAcquireTimeout))
                        .maxIdleTime(Duration.ofSeconds(maxIdleTime))
                        .maxLifeTime(Duration.ofSeconds(maxLifeTime))
                        .build();

        return webclientBuilder
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
                .baseUrl(amadeusUrl)
                .build();
    }
}
