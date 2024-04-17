package com.branet.cloud.dev.suite.sprintservice.service;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateSprintRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import com.branet.cloud.dev.suite.sprintservice.model.SprintStatus;
import com.branet.cloud.dev.suite.sprintservice.repository.SprintRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Sprint service. */
@Service
@Transactional
@RequiredArgsConstructor
public class SprintService {
  private final SprintRepository sprintRepository;

  /**
   * Create sprint sprint.
   *
   * @param createSprintRequest the create sprint request
   * @return the sprint
   */
  public Sprint createSprint(CreateSprintRequest createSprintRequest) {
    Sprint sprint = new Sprint();
    sprint.setStatus(SprintStatus.OPEN);
    sprint.setTitle(createSprintRequest.title());
    sprint.setProjectId(createSprintRequest.projectId());
    sprint.setStartDate(LocalDate.now());
    sprint.setFinishDate(LocalDate.parse(createSprintRequest.finishDate()));

    return sprintRepository.save(sprint);
  }

  /**
   * Get sprint sprint.
   *
   * @param sprintId the sprint id
   * @return the sprint
   */
  public Sprint getSprint(Long sprintId) {
    return sprintRepository.findById(sprintId).orElseThrow(() -> new EntityNotFoundException());
  }

  /**
   * Finish sprint sprint.
   *
   * @param sprintId the sprint id
   * @return the sprint
   */
  public Sprint finishSprint(Long sprintId) {
    Sprint sprint =
        sprintRepository.findById(sprintId).orElseThrow(() -> new EntityNotFoundException());

    sprint.setStatus(SprintStatus.CLOSED);
    sprint.setFinishDate(LocalDate.now());
    return sprintRepository.save(sprint);
  }

  /**
   * Get current sprint sprint.
   *
   * @param projectId the project id
   * @return the sprint
   */
  public Sprint getCurrentSprint(Long projectId) {
    return sprintRepository
        .findCurrentByProjectId(projectId)
        .orElseThrow(() -> new EntityNotFoundException());
  }

  /**
   * Gets all by project.
   *
   * @param projectId the project id
   * @return the all by project
   */
  public List<Sprint> getAllByProject(Long projectId) {
    return sprintRepository.findAllByProjectId(projectId);
  }
}
