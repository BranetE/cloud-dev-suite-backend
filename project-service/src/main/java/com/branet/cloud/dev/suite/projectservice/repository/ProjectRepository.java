package com.branet.cloud.dev.suite.projectservice.repository;

import com.branet.cloud.dev.suite.projectservice.model.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** The interface Project repository. */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  /**
   * Find all by employees contains list.
   *
   * @param employeeId the employee id
   * @return the list
   */
  @Query(
      value =
          "SELECT * FROM projects JOIN project_employees ON projects.id = project_employees.project_id WHERE project_employees.employees=:employeeId",
      nativeQuery = true)
  List<Project> findAllByEmployeesContains(@Param("employeeId") Long employeeId);

  /**
   * Update project status.
   *
   * @param projectId the project id
   * @param status the status
   */
  @Modifying
  @Query(value = "UPDATE projects SET status=:status WHERE id=:projectId", nativeQuery = true)
  void updateProjectStatus(@Param("projectId") Long projectId, @Param("status") String status);

  /**
   * Update responsible employee id.
   *
   * @param projectId the project id
   * @param employeeId the employee id
   */
  @Modifying
  @Query(
      value = "UPDATE projects SET responsible_employee_id=:employeeId WHERE id=:projectId",
      nativeQuery = true)
  void updateResponsibleEmployeeId(
      @Param("projectId") Long projectId, @Param("employeeId") Long employeeId);
}
