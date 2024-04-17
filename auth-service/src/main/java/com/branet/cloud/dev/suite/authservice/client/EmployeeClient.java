package com.branet.cloud.dev.suite.authservice.client;

import com.branet.cloud.dev.suite.authservice.dto.RegisterRequest;
import com.branet.cloud.dev.suite.authservice.security.UserDetailsImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/** The interface Employee client. */
@FeignClient(name = "employee-service", url = "http://localhost:8050")
public interface EmployeeClient {
  /**
   * Gets by email.
   *
   * @param email the email
   * @return the by email
   */
  @GetMapping("/employee/getByEmail")
  UserDetailsImpl getByEmail(@RequestParam("email") String email);

  /**
   * Create employee.
   *
   * @param registerRequest the register request
   */
  @PostMapping("/employee")
  void createEmployee(@RequestBody RegisterRequest registerRequest);
}
