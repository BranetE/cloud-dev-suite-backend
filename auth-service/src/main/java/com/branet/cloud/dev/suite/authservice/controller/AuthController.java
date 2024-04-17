package com.branet.cloud.dev.suite.authservice.controller;

import com.branet.cloud.dev.suite.authservice.dto.LoginRequest;
import com.branet.cloud.dev.suite.authservice.dto.LoginResponse;
import com.branet.cloud.dev.suite.authservice.dto.RegisterRequest;
import com.branet.cloud.dev.suite.authservice.security.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The type Auth controller. */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthServiceImpl authService;

  /**
   * Register.
   *
   * @param registerRequest the register request
   */
  @PostMapping("/register")
  public void register(@RequestBody RegisterRequest registerRequest) {
    authService.register(registerRequest);
  }

  /**
   * Login login response.
   *
   * @param loginRequest the login request
   * @return the login response
   */
  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }
}
