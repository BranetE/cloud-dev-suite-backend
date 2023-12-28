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

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByProjectsContaining(Long projectId);
    @Query(value = "SELECT * FROM employees WHERE email = :email LIMIT 1",
    nativeQuery = true)
    Optional<Employee> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM employees WHERE position=:position AND experience=COALESCE(:experience, experience)",
    nativeQuery = true)
    List<Employee> findByPositionAndExperience(@Param("position") String position, @Param("experience") String experience);
}
