package com.branet.cloud.dev.suite.userservice.controller;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllByProjectId(){
        return employeeService.getAll();
    }

    @GetMapping("/employee/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllByProjectId(@PathVariable("projectId") Long projectId){
        return employeeService.getAllByProjectId(projectId);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        return employeeService.createEmployee(createEmployeeRequest);
    }

    @GetMapping("/employee/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployee(employeeId);
    }
    @PatchMapping("/employee/{employeeId}/addProject")
    @ResponseStatus(HttpStatus.OK)
    public void addProjectToEmployee(@PathVariable Long employeeId, @RequestParam Long projectId){
        employeeService.addProjectToEmployee(employeeId, projectId);
    }

    @PatchMapping("/employee/{employeeId}/removeProject")
    @ResponseStatus(HttpStatus.OK)
    public void removeProjectFromEmployee(@PathVariable Long employeeId, @RequestParam Long projectId) {
        employeeService.removeProjectFromEmployee(employeeId, projectId);
    }

    @GetMapping("/employee/getByEmail")
    @ResponseStatus(HttpStatus.OK)
    public Employee getByEmail(@RequestParam String email){
        return employeeService.getEmployee(email);
    }

    @PutMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest, @PathVariable Long employeeId){
        return employeeService.updateEmployee(createEmployeeRequest, employeeId);
    }

    @DeleteMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }


}
