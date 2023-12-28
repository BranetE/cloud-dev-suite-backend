package com.branet.cloud.dev.suite.projectservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employeeClient")
public interface EmployeeClient {
    @PatchMapping("/employee/{employeeId}/addProject")
    void addProjectToEmployee(@PathVariable Long employeeId, @RequestParam Long projectId);
    @PatchMapping("/employee/{employeeId}/removeProject")
    void removeProjectFromEmployee(@PathVariable Long employeeId, @RequestParam Long projectId);
}
