package com.branet.cloud.dev.suite.authservice.controller;

import com.branet.cloud.dev.suite.authservice.client.EmployeeClient;
import com.branet.cloud.dev.suite.authservice.dto.LoginRequest;
import com.branet.cloud.dev.suite.authservice.dto.LoginResponse;
import com.branet.cloud.dev.suite.authservice.dto.RegisterRequest;
import com.branet.cloud.dev.suite.authservice.security.UserDetailsImpl;
import com.branet.cloud.dev.suite.authservice.security.jwt.JwtUtil;
import com.branet.cloud.dev.suite.authservice.security.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest){
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
