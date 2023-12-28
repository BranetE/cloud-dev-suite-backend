package com.branet.cloud.dev.suite.userservice.repository;

import com.branet.cloud.dev.suite.userservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByProjectsContaining(Long projectId);
    @Query(value = "SELECT * FROM employee_service WHERE email = :email LIMIT 1",
    nativeQuery = true)
    Optional<Employee> findByEmail(@Param("email") String email);
}
