package com.branet.cloud.dev.suite.userservice.dto;

public record CreateEmployeeRequest(
        String email,
        String firstName,
        String lastName,
        String position,
        String experience){}