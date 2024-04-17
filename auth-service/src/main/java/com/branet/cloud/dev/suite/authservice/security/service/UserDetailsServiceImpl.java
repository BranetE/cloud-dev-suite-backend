package com.branet.cloud.dev.suite.authservice.security.service;

import com.branet.cloud.dev.suite.authservice.client.EmployeeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** The type User details service. */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final EmployeeClient employeeClient;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return employeeClient.getByEmail(username);
  }
}
