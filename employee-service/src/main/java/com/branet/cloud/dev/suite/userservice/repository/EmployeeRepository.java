package com.branet.cloud.dev.suite.userservice.repository;

import com.branet.cloud.dev.suite.userservice.model.Employee;
import com.branet.cloud.dev.suite.userservice.model.Experience;
import com.branet.cloud.dev.suite.userservice.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** The interface Employee repository. */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  /**
   * Find all by projects containing list.
   *
   * @param projectId the project id
   * @return the list
   */
  List<Employee> findAllByProjectsContaining(Long projectId);

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  @Query(value = "SELECT * FROM employees WHERE email = :email LIMIT 1", nativeQuery = true)
  Optional<Employee> findByEmail(@Param("email") String email);

  /**
   * Find by position and experience list.
   *
   * @param position the position
   * @param experience the experience
   * @return the list
   */
  @Query(
      value =
          "SELECT * FROM employees WHERE position=:position AND experience=COALESCE(:experience, experience)",
      nativeQuery = true)
  List<Employee> findByPositionAndExperience(
      @Param("position") String position, @Param("experience") String experience);
}
