package com.branet.cloud.dev.suite.shiftservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "shifts")
@Getter
@Setter
@NoArgsConstructor
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime startTime;
    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;
    @Column
    private LocalDateTime endTime;
    @ElementCollection
    private Set<Long> tasks;
    @Column
    private Long employeeId;

    public void addTask(Long taskId) {
        tasks.add(taskId);
    }
    public void removeTask(Long taskId) {
        tasks.remove(taskId);
    }
}
