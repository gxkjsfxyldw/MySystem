package com.ldw.gatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("login-service", r -> r.path("/api/login/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://login-service"))
            .route("config-service", r -> r.path("/api/config/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://config-service"))
            .build();
    }
}