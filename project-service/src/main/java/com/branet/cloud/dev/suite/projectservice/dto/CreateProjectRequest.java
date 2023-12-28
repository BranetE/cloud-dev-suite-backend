package com.branet.cloud.dev.suite.projectservice.dto;

public record CreateProjectRequest(String title, String description, Long responsibleEmployeeId) {
}
