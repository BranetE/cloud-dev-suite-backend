package com.branet.cloud.dev.suite.userservice.service;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.dto.mapper.EmployeeMapper;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Employee service. */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

  /** The Employee mapper. */
  protected final EmployeeMapper employeeMapper;

  private final EmployeeRepository employeeRepository;

  /**
   * Get employee employee.
   *
   * @param employeeId the employee id
   * @return the employee
   */
  public Employee getEmployee(Long employeeId) {
    return employeeRepository.findById(employeeId).orElseThrow(EntityNotFoundException::new);
  }

  /**
   * Get by position and experience list.
   *
   * @param position the position
   * @param experience the experience
   * @return the list
   */
  public List<Employee> getByPositionAndExperience(String position, String experience) {
    return employeeRepository.findByPositionAndExperience(position, experience);
  }

  /**
   * Gets employee.
   *
   * @param email the email
   * @return the employee
   */
  public Employee getEmployee(String email) {
    return employeeRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  public List<Employee> getAll() {
    return employeeRepository.findAll();
  }

  /**
   * Get all by project id list.
   *
   * @param projectId the project id
   * @return the list
   */
  public List<Employee> getAllByProjectId(Long projectId) {
    return employeeRepository.findAllByProjectsContaining(projectId);
  }

  /**
   * Add project to employee.
   *
   * @param employeeId the employee id
   * @param projectId the project id
   */
  public void addProjectToEmployee(Long employeeId, Long projectId) {
    Employee employee =
        employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException());
    employee.addProject(projectId);
    employeeRepository.save(employee);
  }

  /**
   * Remove project from employee.
   *
   * @param employeeId the employee id
   * @param projectId the project id
   */
  public void removeProjectFromEmployee(Long employeeId, Long projectId) {
    Employee employee =
        employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException());
    employee.removeProject(projectId);
    employeeRepository.save(employee);
  }

  /**
   * Create employee employee.
   *
   * @param createEmployeeRequest the create employee request
   * @return the employee
   */
  public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) {
    Employee employee = employeeMapper.dtoToEntity(createEmployeeRequest);
    return employeeRepository.save(employee);
  }

  /**
   * Update employee employee.
   *
   * @param createEmployeeRequest the create employee request
   * @param employeeId the employee id
   * @return the employee
   */
  public Employee updateEmployee(CreateEmployeeRequest createEmployeeRequest, Long employeeId) {
    Employee employee = employeeMapper.dtoToEntity(createEmployeeRequest);
    employee.setId(employeeId);
    return employeeRepository.save(employee);
  }

  /**
   * Delete employee.
   *
   * @param employeeId the employee id
   */
  public void deleteEmployee(Long employeeId) {
    employeeRepository.deleteById(employeeId);
  }
}
