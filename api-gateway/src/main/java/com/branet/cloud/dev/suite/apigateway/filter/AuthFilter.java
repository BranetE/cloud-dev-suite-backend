package com.branet.cloud.dev.suite.apigateway.filter;

import com.branet.cloud.dev.suite.apigateway.dto.EmployeeDto;
import com.branet.cloud.dev.suite.apigateway.security.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final JwtUtil jwtUtil;


    public AuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Missing auth header");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");


            if(parts.length != 2 || !"Bearer".equals(parts[0])){
                throw new RuntimeException("Incorrect token");
            }

            if(jwtUtil.isTokenExpired(parts[1])){
                throw new RuntimeException("Expired token");
            }

            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
