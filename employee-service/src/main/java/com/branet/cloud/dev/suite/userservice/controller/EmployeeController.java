package com.branet.cloud.dev.suite.userservice.controller;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** The type Employee controller. */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  /**
   * Gets all.
   *
   * @return the all
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Employee> getAll() {
    return employeeService.getAll();
  }

  /**
   * Gets all by project id.
   *
   * @param projectId the project id
   * @return the all by project id
   */
  @GetMapping("/getAllByProject/{projectId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Employee> getAllByProjectId(@PathVariable("projectId") Long projectId) {
    return employeeService.getAllByProjectId(projectId);
  }

  /**
   * Create employee employee.
   *
   * @param createEmployeeRequest the create employee request
   * @return the employee
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Employee createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
    return employeeService.createEmployee(createEmployeeRequest);
  }

  /**
   * Gets by position and or experience.
   *
   * @param position the position
   * @param experience the experience
   * @return the by position and or experience
   */
  @GetMapping("/getByPositionAndOrExperience")
  //    @PreAuthorize("hasAnyAuthority('TEAM_LEAD', 'MANAGER')")
  @ResponseStatus(HttpStatus.OK)
  public List<Employee> getByPositionAndOrExperience(
      @RequestParam(required = false) String position,
      @RequestParam(required = false) String experience) {
    return employeeService.getByPositionAndExperience(position, experience);
  }

  /**
   * Gets employee by id.
   *
   * @param employeeId the employee id
   * @return the employee by id
   */
  @GetMapping("/{employeeId}")
  @ResponseStatus(HttpStatus.OK)
  public Employee getEmployeeById(@PathVariable Long employeeId) {
    return employeeService.getEmployee(employeeId);
  }

  /**
   * Add project to employee.
   *
   * @param employeeId the employee id
   * @param projectId the project id
   */
  @PutMapping("/{employeeId}/addProject")
  @ResponseStatus(HttpStatus.OK)
  public void addProjectToEmployee(@PathVariable Long employeeId, @RequestParam Long projectId) {
    employeeService.addProjectToEmployee(employeeId, projectId);
  }

  /**
   * Remove project from employee.
   *
   * @param employeeId the employee id
   * @param projectId the project id
   */
  @PutMapping("/{employeeId}/removeProject")
  @ResponseStatus(HttpStatus.OK)
  public void removeProjectFromEmployee(
      @PathVariable Long employeeId, @RequestParam Long projectId) {
    employeeService.removeProjectFromEmployee(employeeId, projectId);
  }

  /**
   * Gets by email.
   *
   * @param email the email
   * @return the by email
   */
  @GetMapping("/getByEmail")
  @ResponseStatus(HttpStatus.OK)
  public Employee getByEmail(@RequestParam String email) {
    return employeeService.getEmployee(email);
  }

  /**
   * Update employee employee.
   *
   * @param createEmployeeRequest the create employee request
   * @param employeeId the employee id
   * @return the employee
   */
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public Employee updateEmployee(
      @RequestBody CreateEmployeeRequest createEmployeeRequest, @PathVariable Long employeeId) {
    return employeeService.updateEmployee(createEmployeeRequest, employeeId);
  }

  /**
   * Delete employee.
   *
   * @param employeeId the employee id
   */
  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteEmployee(@PathVariable Long employeeId) {
    employeeService.deleteEmployee(employeeId);
  }
}
