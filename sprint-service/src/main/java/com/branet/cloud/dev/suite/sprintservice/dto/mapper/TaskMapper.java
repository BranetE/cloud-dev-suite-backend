package com.branet.cloud.dev.suite.sprintservice.dto.mapper;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task dtoToEntity(CreateTaskRequest createTaskRequest);
}
