package com.branet.cloud.dev.suite.userservice.dto.mapper;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import org.mapstruct.Mapper;

/** The interface Employee mapper. */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
  /**
   * Dto to entity employee.
   *
   * @param createEmployeeRequest the create employee request
   * @return the employee
   */
  Employee dtoToEntity(CreateEmployeeRequest createEmployeeRequest);
}
