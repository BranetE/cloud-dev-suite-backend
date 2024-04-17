package com.branet.cloud.dev.suite.authservice.security.service;

import com.branet.cloud.dev.suite.authservice.client.EmployeeClient;
import com.branet.cloud.dev.suite.authservice.dto.LoginRequest;
import com.branet.cloud.dev.suite.authservice.dto.LoginResponse;
import com.branet.cloud.dev.suite.authservice.dto.RegisterRequest;
import com.branet.cloud.dev.suite.authservice.security.UserDetailsImpl;
import com.branet.cloud.dev.suite.authservice.security.jwt.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** The type Auth service. */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

  private final EmployeeClient employeeClient;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  /**
   * Register.
   *
   * @param registerRequest the register request
   */
  public void register(RegisterRequest registerRequest) {
    String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
    registerRequest.setPassword(encodedPassword);

    employeeClient.createEmployee(registerRequest);
  }

  /**
   * Login login response.
   *
   * @param loginRequest the login request
   * @return the login response
   */
  public LoginResponse login(LoginRequest loginRequest) {
    UserDetailsImpl userDetails = employeeClient.getByEmail(loginRequest.email());
    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("id", userDetails.getId());
    extraClaims.put(
        "position", userDetails.getAuthorities().stream().findFirst().get().getAuthority());
    String token = jwtUtil.generateToken(extraClaims, userDetails.getEmail());
    return new LoginResponse(token, token);
  }
}
