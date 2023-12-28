package com.branet.cloud.dev.suite.userservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String position;
    private String password;
    private String experience;
}