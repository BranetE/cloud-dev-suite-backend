package com.branet.cloud.dev.suite.userservice.service;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.dto.mapper.EmployeeMapper;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.repository.EmployeeRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    protected final EmployeeMapper employeeMapper;

    public Employee getEmployee(Long employeeId){
        return employeeRepository.findById(employeeId).orElseThrow(EntityNotFoundException::new);
    }

    public List<Employee> getByPositionAndExperience(String position, String experience){
        return employeeRepository.findByPositionAndExperience(position, experience);
    }

    public Employee getEmployee(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllByProjectId(Long projectId){
        return employeeRepository.findAllByProjectsContaining(projectId);
    }

    public void addProjectToEmployee(Long employeeId, Long projectId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException());
        employee.addProject(projectId);
        employeeRepository.save(employee);
    }

    public void removeProjectFromEmployee(Long employeeId, Long projectId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException());
        employee.removeProject(projectId);
        employeeRepository.save(employee);
    }

    public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest){
        Employee employee = employeeMapper.dtoToEntity(createEmployeeRequest);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(CreateEmployeeRequest createEmployeeRequest, Long employeeId){
        Employee employee = employeeMapper.dtoToEntity(createEmployeeRequest);
        employee.setId(employeeId);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }
}
