package com.branet.cloud.dev.suite.projectservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Project. */
@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String title;

  @Column(length = 500)
  @Size(min = 255, max = 500)
  private String description;

  @Enumerated(EnumType.STRING)
  private ProjectStatus status;

  @Column private LocalDate startDate;
  @Column private Long responsibleEmployeeId;
  @ElementCollection private Set<Long> employees;

  /**
   * Add employee.
   *
   * @param employeeId the employee id
   */
  public void addEmployee(Long employeeId) {
    employees.add(employeeId);
  }

  /**
   * Remove employee.
   *
   * @param employeeId the employee id
   */
  public void removeEmployee(Long employeeId) {
    employees.remove(employeeId);
  }
}
