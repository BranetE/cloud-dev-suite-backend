package com.branet.cloud.dev.suite.authservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest{
    String email;
    String firstName;
    String lastName;
    String position;
    String password;
    String experience;
}
