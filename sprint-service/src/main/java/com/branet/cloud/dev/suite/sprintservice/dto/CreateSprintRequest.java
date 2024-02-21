package com.branet.cloud.dev.suite.sprintservice.dto;

public record CreateSprintRequest(
        String title,
        Long projectId,
        String finishDate
) {}
