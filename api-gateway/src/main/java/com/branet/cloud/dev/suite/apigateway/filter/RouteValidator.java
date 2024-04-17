package com.branet.cloud.dev.suite.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/** The type Route validator. */
@Component
public class RouteValidator {

  /** The constant openApiEndpoints. */
  public static final List<String> openApiEndpoints =
      List.of("/auth/register", "/auth/login", "/eureka");

  /** The Is secured. */
  public Predicate<ServerHttpRequest> isSecured =
      request ->
          openApiEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
}
