package com.branet.cloud.dev.suite.projectservice.service;

import com.branet.cloud.dev.suite.projectservice.client.EmployeeClient;
import com.branet.cloud.dev.suite.projectservice.dto.CreateProjectRequest;
import com.branet.cloud.dev.suite.projectservice.model.Project;
import com.branet.cloud.dev.suite.projectservice.model.ProjectStatus;
import com.branet.cloud.dev.suite.projectservice.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeClient employeeClient;

    public Project createProject(CreateProjectRequest createProjectRequest){
        Project project = new Project();
        project.setTitle(createProjectRequest.title());
        project.setDescription(createProjectRequest.description());
        project.setStatus(ProjectStatus.DEVELOPMENT);
        project.setResponsibleEmployeeId(createProjectRequest.responsibleEmployeeId());
        project.setStartDate(LocalDate.now());

        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<Project> getAllProjectsByEmployee(Long employeeId){
        return projectRepository.findAllByEmployeesContains(employeeId);
    }

    public void addEmployeeToProject(Long projectId, Long employeeId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
        project.addEmployee(employeeId);
        projectRepository.save(project);
        employeeClient.addProjectToEmployee(employeeId, project.getId());
    }

    public void removeEmployeeFromProject(Long projectId, Long employeeId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
        project.removeEmployee(employeeId);
        projectRepository.save(project);
        employeeClient.removeProjectFromEmployee(employeeId, project.getId());
    }

    public Project getProject(Long projectId){
        return projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
    }

    public void changeStatus(Long projectId, String status){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException());
        ProjectStatus.valueOf(status);
        if (!project.getStatus().equals(ProjectStatus.FINISHED)){
            projectRepository.updateProjectStatus(projectId, status);
        }
    }

    public void changeResponsibleEmployee(Long projectId, Long employeeId){
        projectRepository.updateResponsibleEmployeeId(projectId, employeeId);
    }
}
