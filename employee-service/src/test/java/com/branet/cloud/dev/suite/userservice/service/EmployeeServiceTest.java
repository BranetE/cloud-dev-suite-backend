package com.branet.cloud.dev.suite.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.dto.mapper.EmployeeMapper;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EmployeeServiceTest {

  @Mock private EmployeeRepository employeeRepository;

  @Mock private EmployeeMapper employeeMapper;

  @InjectMocks private EmployeeService employeeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetEmployee() {
    Long employeeId = 1L;
    Employee employee = new Employee();
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

    Employee result = employeeService.getEmployee(employeeId);

    assertNotNull(result);
    assertEquals(employee, result);
    verify(employeeRepository, times(1)).findById(employeeId);
  }

  @Test
  void testGetByPositionAndExperience() {
    String position = "Developer";
    String experience = "Intermediate";
    List<Employee> employees = new ArrayList<>();
    when(employeeRepository.findByPositionAndExperience(position, experience))
        .thenReturn(employees);

    List<Employee> result = employeeService.getByPositionAndExperience(position, experience);

    assertNotNull(result);
    assertEquals(employees, result);
    verify(employeeRepository, times(1)).findByPositionAndExperience(position, experience);
  }

  @Test
  void testGetEmployeeByEmail() {
    String email = "john.doe@example.com";
    Employee employee = new Employee();
    when(employeeRepository.findByEmail(email)).thenReturn(Optional.of(employee));

    Employee result = employeeService.getEmployee(email);

    assertNotNull(result);
    assertEquals(employee, result);
    verify(employeeRepository, times(1)).findByEmail(email);
  }

  @Test
  void testGetAll() {
    List<Employee> employees = new ArrayList<>();
    when(employeeRepository.findAll()).thenReturn(employees);

    List<Employee> result = employeeService.getAll();

    assertNotNull(result);
    assertEquals(employees, result);
    verify(employeeRepository, times(1)).findAll();
  }

  @Test
  void testGetAllByProjectId() {
    Long projectId = 1L;
    List<Employee> employees = new ArrayList<>();
    when(employeeRepository.findAllByProjectsContaining(projectId)).thenReturn(employees);

    List<Employee> result = employeeService.getAllByProjectId(projectId);

    assertNotNull(result);
    assertEquals(employees, result);
    verify(employeeRepository, times(1)).findAllByProjectsContaining(projectId);
  }

  @Test
  void testAddProjectToEmployee() {
    Long employeeId = 1L;
    Long projectId = 1L;
    Employee employee = new Employee();
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

    employeeService.addProjectToEmployee(employeeId, projectId);

    verify(employeeRepository, times(1)).save(employee);
  }

  @Test
  void testRemoveProjectFromEmployee() {
    Long employeeId = 1L;
    Long projectId = 1L;
    Employee employee = new Employee();
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

    employeeService.removeProjectFromEmployee(employeeId, projectId);

    verify(employeeRepository, times(1)).save(employee);
  }

  @Test
  void testCreateEmployee() {
    CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
    Employee employee = new Employee();
    when(employeeMapper.dtoToEntity(createEmployeeRequest)).thenReturn(employee);
    when(employeeRepository.save(employee)).thenReturn(employee);

    Employee result = employeeService.createEmployee(createEmployeeRequest);

    assertNotNull(result);
    assertEquals(employee, result);
    verify(employeeRepository, times(1)).save(employee);
  }

  @Test
  void testUpdateEmployee() {
    Long employeeId = 1L;
    CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
    Employee employee = new Employee();
    when(employeeMapper.dtoToEntity(createEmployeeRequest)).thenReturn(employee);
    when(employeeRepository.save(employee)).thenReturn(employee);

    Employee result = employeeService.updateEmployee(createEmployeeRequest, employeeId);

    assertNotNull(result);
    assertEquals(employee, result);
    verify(employeeRepository, times(1)).save(employee);
  }

  @Test
  void testDeleteEmployee() {
    Long employeeId = 1L;

    employeeService.deleteEmployee(employeeId);

    verify(employeeRepository, times(1)).deleteById(employeeId);
  }
}
