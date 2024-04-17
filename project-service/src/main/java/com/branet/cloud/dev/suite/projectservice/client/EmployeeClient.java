package com.branet.cloud.dev.suite.projectservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** The interface Employee client. */
@FeignClient(name = "employee-service", url = "http://localhost:8050")
public interface EmployeeClient {
  /**
   * Add project to employee.
   *
   * @param employeeId the employee id
   * @param projectId the project id
   */
  @PutMapping("/employee/{employeeId}/addProject")
  void addProjectToEmployee(@PathVariable Long employeeId, @RequestParam Long projectId);

  /**
   * Remove project from employee.
   *
   * @param employeeId the employee id
   * @param projectId the project id
   */
  @PutMapping("/employee/{employeeId}/removeProject")
  void removeProjectFromEmployee(@PathVariable Long employeeId, @RequestParam Long projectId);
}
