package com.branet.cloud.dev.suite.sprintservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Sprint. */
@Entity
@Table(name = "sprints")
@Getter
@Setter
@NoArgsConstructor
public class Sprint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String title;

  @Column private LocalDate startDate;

  @Enumerated(EnumType.STRING)
  private SprintStatus status;

  @Column private LocalDate finishDate;

  @Column private Long projectId;

  @OneToMany(mappedBy = "sprint")
  @JsonIgnore
  private List<Task> tasks;
}
