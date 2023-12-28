package com.branet.cloud.dev.suite.authservice.client;

import com.branet.cloud.dev.suite.authservice.dto.RegisterRequest;
import com.branet.cloud.dev.suite.authservice.security.UserDetailsImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employeeClient")
public interface EmployeeClient {
    @GetMapping("/employee")
    UserDetailsImpl getByEmail(@RequestParam("email") String email);

    @PostMapping("/employee")
    void createEmployee(@RequestBody RegisterRequest registerRequest);
}
