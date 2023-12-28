package com.branet.cloud.dev.suite.projectservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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
    @Column
    private LocalDate startDate;
    @Column
    private Long responsibleEmployeeId;
    @ElementCollection
    private Set<Long> employees;

    public void addEmployee(Long employeeId){
        employees.add(employeeId);
    }

    public void  removeEmployee(Long employeeId){
        employees.remove(employeeId);
    }
}
