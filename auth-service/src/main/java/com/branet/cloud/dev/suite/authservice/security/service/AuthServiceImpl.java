package com.branet.cloud.dev.suite.authservice.security.service;

import com.branet.cloud.dev.suite.authservice.client.EmployeeClient;
import com.branet.cloud.dev.suite.authservice.dto.LoginRequest;
import com.branet.cloud.dev.suite.authservice.dto.LoginResponse;
import com.branet.cloud.dev.suite.authservice.dto.RegisterRequest;
import com.branet.cloud.dev.suite.authservice.security.UserDetailsImpl;
import com.branet.cloud.dev.suite.authservice.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final EmployeeClient employeeClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest registerRequest){
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        registerRequest.setPassword(encodedPassword);

        employeeClient.createEmployee(registerRequest);
    }

    public LoginResponse login(LoginRequest loginRequest){
        UserDetailsImpl userDetails = employeeClient.getByEmail(loginRequest.email());
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", userDetails.getId());
        extraClaims.put("position",
                userDetails.getAuthorities().stream()
                        .findFirst()
                        .get()
                        .getAuthority());
        String token = jwtUtil.generateToken(extraClaims, userDetails.getEmail());
        return new LoginResponse(token, token);
    }
}
