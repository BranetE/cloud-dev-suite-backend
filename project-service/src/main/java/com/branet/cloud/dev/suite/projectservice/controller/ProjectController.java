package com.branet.cloud.dev.suite.projectservice.controller;

import com.branet.cloud.dev.suite.projectservice.dto.CreateProjectRequest;
import com.branet.cloud.dev.suite.projectservice.model.Project;
import com.branet.cloud.dev.suite.projectservice.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** The type Project controller. */
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
  private final ProjectService projectService;

  /**
   * Get all list.
   *
   * @return the list
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Project> getAll() {
    return projectService.getAll();
  }

  /**
   * Gets all by employee id.
   *
   * @param employeeId the employee id
   * @return the all by employee id
   */
  @GetMapping("/getByEmployee/{employeeId}")
  @PreAuthorize("hasAuthority('MANAGER') or authentication.principal.id == #employeeId")
  @ResponseStatus(HttpStatus.OK)
  public List<Project> getAllByEmployeeId(@PathVariable("employeeId") Long employeeId) {
    return projectService.getAllProjectsByEmployee(employeeId);
  }

  /**
   * Create project project.
   *
   * @param createProjectRequest the create project request
   * @return the project
   */
  @PostMapping
  @PreAuthorize("hasAuthority('MANAGER')")
  @ResponseStatus(HttpStatus.OK)
  public Project createProject(@RequestBody CreateProjectRequest createProjectRequest) {
    return projectService.createProject(createProjectRequest);
  }

  /**
   * Get project project.
   *
   * @param projectId the project id
   * @return the project
   */
  @GetMapping("/{projectId}")
  @ResponseStatus(HttpStatus.OK)
  public Project getProject(@PathVariable Long projectId) {
    return projectService.getProject(projectId);
  }

  /**
   * Add employee.
   *
   * @param projectId the project id
   * @param employeeId the employee id
   */
  @PatchMapping("/{projectId}/addEmployee/{employeeId}")
  @PreAuthorize("hasAuthority('TEAM_LEAD')")
  @ResponseStatus(HttpStatus.OK)
  public void addEmployee(
      @PathVariable("projectId") Long projectId, @PathVariable("employeeId") Long employeeId) {
    projectService.addEmployeeToProject(projectId, employeeId);
  }

  /**
   * Remove employee.
   *
   * @param projectId the project id
   * @param employeeId the employee id
   */
  @PatchMapping("/{projectId}/removeEmployee/{employeeId}")
  @PreAuthorize("hasAuthority('TEAM_LEAD')")
  @ResponseStatus(HttpStatus.OK)
  public void removeEmployee(
      @PathVariable("projectId") Long projectId, @PathVariable("employeeId") Long employeeId) {
    projectService.removeEmployeeFromProject(projectId, employeeId);
  }

  /**
   * Change status.
   *
   * @param projectId the project id
   * @param status the status
   */
  @PatchMapping("/changeStatus/{projectId}")
  @PreAuthorize("hasAuthority('MANAGER')")
  @ResponseStatus(HttpStatus.OK)
  public void changeStatus(@PathVariable Long projectId, @RequestParam("status") String status) {
    projectService.changeStatus(projectId, status);
  }

  /**
   * Change team lead.
   *
   * @param projectId the project id
   * @param responsibleEmployeeId the responsible employee id
   */
  @PatchMapping("/changeResponsibleEmployee/{projectId}")
  @PreAuthorize("hasAuthority('MANAGER')")
  @ResponseStatus(HttpStatus.OK)
  public void changeTeamLead(
      @PathVariable Long projectId, @RequestParam("employeeId") Long responsibleEmployeeId) {
    projectService.changeResponsibleEmployee(projectId, responsibleEmployeeId);
  }
}
