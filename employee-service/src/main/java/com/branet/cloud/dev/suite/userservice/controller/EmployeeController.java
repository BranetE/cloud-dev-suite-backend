package com.branet.cloud.dev.suite.userservice.controller;

import com.branet.cloud.dev.suite.userservice.dto.CreateEmployeeRequest;
import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/getAllByProject/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllByProjectId(@PathVariable("projectId") Long projectId){
        return employeeService.getAllByProjectId(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        return employeeService.createEmployee(createEmployeeRequest);
    }

    @GetMapping("/getByPositionAndOrExperience")
//    @PreAuthorize("hasAnyAuthority('TEAM_LEAD', 'MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getByPositionAndOrExperience(@RequestParam(required = false) String position, @RequestParam(required = false) String experience){
        return employeeService.getByPositionAndExperience(position, experience);
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @PutMapping("/{employeeId}/addProject")
    @ResponseStatus(HttpStatus.OK)
    public void addProjectToEmployee(@PathVariable Long employeeId, @RequestParam Long projectId){
        employeeService.addProjectToEmployee(employeeId, projectId);
    }

    @PutMapping("/{employeeId}/removeProject")
    @ResponseStatus(HttpStatus.OK)
    public void removeProjectFromEmployee(@PathVariable Long employeeId, @RequestParam Long projectId) {
        employeeService.removeProjectFromEmployee(employeeId, projectId);
    }

    @GetMapping("/getByEmail")
    @ResponseStatus(HttpStatus.OK)
    public Employee getByEmail(@RequestParam String email){
        return employeeService.getEmployee(email);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest, @PathVariable Long employeeId){
        return employeeService.updateEmployee(createEmployeeRequest, employeeId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }


}
