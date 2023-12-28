package com.branet.cloud.dev.suite.sprintservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(length = 500)
    @Size(min = 255, max = 500)
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Column(length = 500)
    private String comment;
    @Column
    private Long employeeId;
    @Column
    @JsonIgnore
    private Long shiftId;
    @ManyToOne
    @JoinColumn(name = "sprintId")
    private Sprint sprint;
    @Column
    private LocalDateTime finishTime;
}
