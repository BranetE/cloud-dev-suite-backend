package com.branet.cloud.dev.suite.projectservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employee-service", url = "http://localhost:8050")
public interface EmployeeClient {
    @PutMapping("/employee/{employeeId}/addProject")
    void addProjectToEmployee(@PathVariable Long employeeId, @RequestParam Long projectId);
    @PutMapping("/employee/{employeeId}/removeProject")
    void removeProjectFromEmployee(@PathVariable Long employeeId, @RequestParam Long projectId);
}
