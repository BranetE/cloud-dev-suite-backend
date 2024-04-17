package com.branet.cloud.dev.suite.sprintservice.dto;

/** The type Create sprint request. */
public record CreateSprintRequest(String title, Long projectId, String finishDate) {}
