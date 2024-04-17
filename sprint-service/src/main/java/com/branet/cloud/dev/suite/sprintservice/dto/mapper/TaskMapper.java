package com.branet.cloud.dev.suite.sprintservice.dto.mapper;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Task;
import org.mapstruct.Mapper;

/** The interface Task mapper. */
@Mapper(componentModel = "spring")
public interface TaskMapper {
  /**
   * Dto to entity task.
   *
   * @param createTaskRequest the create task request
   * @return the task
   */
  Task dtoToEntity(CreateTaskRequest createTaskRequest);
}
