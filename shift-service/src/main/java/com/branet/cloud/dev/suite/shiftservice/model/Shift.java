package com.branet.cloud.dev.suite.shiftservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Shift. */
@Entity
@Table(name = "shifts")
@Getter
@Setter
@NoArgsConstructor
public class Shift {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private LocalDateTime startTime;

  @Enumerated(EnumType.STRING)
  private ShiftType shiftType;

  @Column private LocalDateTime endTime;
  @ElementCollection private Set<Long> tasks;
  @Column private Long employeeId;

  /**
   * Add task.
   *
   * @param taskId the task id
   */
  public void addTask(Long taskId) {
    tasks.add(taskId);
  }

  /**
   * Remove task.
   *
   * @param taskId the task id
   */
  public void removeTask(Long taskId) {
    tasks.remove(taskId);
  }
}
