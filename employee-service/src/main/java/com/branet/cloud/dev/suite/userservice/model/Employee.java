package com.branet.cloud.dev.suite.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/** The type Employee. */
@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String firstName;
  @Column private String lastName;

  @Column(unique = true)
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;

  @Column private String password;

  @Enumerated(EnumType.STRING)
  private Experience experience;

  @Enumerated(EnumType.STRING)
  private Position position;

  @ElementCollection private Set<Long> projects = new HashSet<>();

  /**
   * Add project.
   *
   * @param projectId the project id
   */
  public void addProject(Long projectId) {
    projects.add(projectId);
  }

  /**
   * Remove project.
   *
   * @param projectId the project id
   */
  public void removeProject(Long projectId) {
    projects.remove(projectId);
  }
}
