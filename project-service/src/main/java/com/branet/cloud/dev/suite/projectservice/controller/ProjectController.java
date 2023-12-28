package com.branet.cloud.dev.suite.projectservice.controller;

import com.branet.cloud.dev.suite.projectservice.dto.CreateProjectRequest;
import com.branet.cloud.dev.suite.projectservice.model.Project;
import com.branet.cloud.dev.suite.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAll(){
        return projectService.getAll();
    }

    @GetMapping("/getByEmployee/{employeeId}")
    @PreAuthorize("hasAuthority('MANAGER') or principal.id = #employeeId")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllByEmployeeId(@PathVariable("employeeId") Long employeeId){
        return projectService.getAllProjectsByEmployee(employeeId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    public Project createProject(@RequestBody CreateProjectRequest createProjectRequest){
        return projectService.createProject(createProjectRequest);
    }

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public Project getProject(@PathVariable Long projectId){
        return projectService.getProject(projectId);
    }

    @PatchMapping("/{projectId}/addEmployee/{employeeId}")
    @PreAuthorize("hasAuthority('TEAM_LEAD')")
    @ResponseStatus(HttpStatus.OK)
    public void addEmployee(@PathVariable("projectId") Long projectId, @PathVariable("employeeId") Long employeeId){
        projectService.addEmployeeToProject(projectId, employeeId);
    }

    @PatchMapping("/{projectId}/removeEmployee/{employeeId}")
    @PreAuthorize("hasAuthority('TEAM_LEAD')")
    @ResponseStatus(HttpStatus.OK)
    public void removeEmployee(@PathVariable("projectId") Long projectId, @PathVariable("employeeId") Long employeeId){
        projectService.removeEmployeeFromProject(projectId, employeeId);
    }

    @PatchMapping("/changeStatus/{projectId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatus(@PathVariable Long projectId, @RequestParam("status") String status){
        projectService.changeStatus(projectId, status);
    }

    @PatchMapping("/changeTeamLead/{projectId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    public void changeResponsibleEmployee(@PathVariable Long projectId, @RequestParam("employeeId") Long responsibleEmployeeId){
        projectService.changeResponsibleEmployee(projectId, responsibleEmployeeId);
    }
}
