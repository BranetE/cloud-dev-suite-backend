package com.branet.cloud.dev.suite.userservice.dto.mapper;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToEntity(CreateEmployeeRequest createEmployeeRequest);
}
