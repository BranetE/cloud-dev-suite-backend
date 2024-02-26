package com.branet.cloud.dev.suite.sprintservice.dto;

public record CreateTaskRequest(
   String title,
   String description,
   Long sprintId) {}
