package com.ldw.gatewayservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus; // 添加 HttpStatus 导入
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestLoggingFilter implements GlobalFilter {

    private static final String SECRET_KEY = "your-secret-key"; // 应从配置中心获取

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        System.out.println("Request Path: " + path);

        // 简单的 JWT 验证逻辑（实际应结合具体认证服务）
        if (path.startsWith("/api/")) {
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (token == null || !validateJwtToken(token)) {
                // 拒绝访问
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }

    private boolean validateJwtToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}