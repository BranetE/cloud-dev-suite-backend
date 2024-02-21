package com.branet.cloud.dev.suite.projectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProjectRequest{
    private String title;
    private String description;
    private String status;
    private Long teamLeadId;
}
