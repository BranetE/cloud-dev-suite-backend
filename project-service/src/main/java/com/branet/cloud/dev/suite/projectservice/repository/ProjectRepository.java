package com.branet.cloud.dev.suite.projectservice.repository;

import com.branet.cloud.dev.suite.projectservice.model.Project;
import com.branet.cloud.dev.suite.projectservice.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByEmployeesContains(Long employeeId);
    @Modifying
    @Query(value = "UPDATE projects AS p SET p.status=:status WHERE p.id=:projectId",
            nativeQuery = true)
    void updateProjectStatus(@Param("projectId") Long projectId, @Param("status") ProjectStatus status);

    @Modifying
    @Query(value = "UPDATE projects AS p SET p.responsibleEmployeeId=:employeeId WHERE p.id=:projectId",
            nativeQuery = true)
    void updateResponsibleEmployeeId(@Param("projectId") Long projectId, @Param("employeeId") Long employeeId);
}
