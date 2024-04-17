package com.branet.cloud.dev.suite.projectservice.service;

import com.branet.cloud.dev.suite.projectservice.client.EmployeeClient;
import com.branet.cloud.dev.suite.projectservice.dto.CreateProjectRequest;
import com.branet.cloud.dev.suite.projectservice.model.Project;
import com.branet.cloud.dev.suite.projectservice.model.ProjectStatus;
import com.branet.cloud.dev.suite.projectservice.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Project service. */
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
  private final ProjectRepository projectRepository;
  private final EmployeeClient employeeClient;

  /**
   * Create project project.
   *
   * @param createProjectRequest the create project request
   * @return the project
   */
  public Project createProject(CreateProjectRequest createProjectRequest) {
    Project project = new Project();
    project.setTitle(createProjectRequest.getTitle());
    project.setStatus(ProjectStatus.valueOf(createProjectRequest.getStatus()));
    project.setDescription(createProjectRequest.getDescription());
    project.setResponsibleEmployeeId(createProjectRequest.getTeamLeadId());
    project.setStartDate(LocalDate.now());
    project.setEmployees(Set.of(createProjectRequest.getTeamLeadId()));

    Project savedProject = projectRepository.save(project);
    employeeClient.addProjectToEmployee(
        savedProject.getResponsibleEmployeeId(), savedProject.getId());
    return savedProject;
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  public List<Project> getAll() {
    return projectRepository.findAll();
  }

  /**
   * Get all projects by employee list.
   *
   * @param employeeId the employee id
   * @return the list
   */
  public List<Project> getAllProjectsByEmployee(Long employeeId) {
    return projectRepository.findAllByEmployeesContains(employeeId);
  }

  /**
   * Add employee to project.
   *
   * @param projectId the project id
   * @param employeeId the employee id
   */
  public void addEmployeeToProject(Long projectId, Long employeeId) {
    Project project =
        projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
    project.addEmployee(employeeId);
    projectRepository.save(project);
    employeeClient.addProjectToEmployee(employeeId, project.getId());
  }

  /**
   * Remove employee from project.
   *
   * @param projectId the project id
   * @param employeeId the employee id
   */
  public void removeEmployeeFromProject(Long projectId, Long employeeId) {
    Project project =
        projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
    project.removeEmployee(employeeId);
    projectRepository.save(project);
    employeeClient.removeProjectFromEmployee(employeeId, project.getId());
  }

  /**
   * Get project project.
   *
   * @param projectId the project id
   * @return the project
   */
  public Project getProject(Long projectId) {
    return projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
  }

  /**
   * Change status.
   *
   * @param projectId the project id
   * @param status the status
   */
  public void changeStatus(Long projectId, String status) {
    Project project =
        projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
    ProjectStatus.valueOf(status);
    if (!project.getStatus().equals(ProjectStatus.FINISHED)) {
      projectRepository.updateProjectStatus(projectId, status);
    }
  }

  /**
   * Change responsible employee.
   *
   * @param projectId the project id
   * @param employeeId the employee id
   */
  public void changeResponsibleEmployee(Long projectId, Long employeeId) {
    projectRepository.updateResponsibleEmployeeId(projectId, employeeId);
  }
}
