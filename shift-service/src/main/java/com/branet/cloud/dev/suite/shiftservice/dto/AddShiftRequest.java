package com.branet.cloud.dev.suite.shiftservice.dto;

import com.branet.cloud.dev.suite.shiftservice.model.Shift;

import java.time.LocalDateTime;

/**
 * DTO for {@link Shift}
 */
public record AddShiftRequest(
        LocalDateTime startTime,
        String shiftType) {
}