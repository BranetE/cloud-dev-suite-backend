package com.branet.cloud.dev.suite.sprintservice.controller;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateSprintRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import com.branet.cloud.dev.suite.sprintservice.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** The type Sprint controller. */
@RestController
@RequestMapping("/sprint")
@RequiredArgsConstructor
public class SprintController {

  private final SprintService sprintService;

  /**
   * Get sprint sprint.
   *
   * @param sprintId the sprint id
   * @return the sprint
   */
  @GetMapping("/{sprintId}")
  @ResponseStatus(HttpStatus.OK)
  public Sprint getSprint(@PathVariable Long sprintId) {
    return sprintService.getSprint(sprintId);
  }

  /**
   * Create sprint sprint.
   *
   * @param createSprintRequest the create sprint request
   * @return the sprint
   */
  @PostMapping("/start")
  @PreAuthorize("hasAuthority('TEAM_LEAD')")
  @ResponseStatus(HttpStatus.CREATED)
  public Sprint createSprint(@RequestBody CreateSprintRequest createSprintRequest) {
    return sprintService.createSprint(createSprintRequest);
  }

  /**
   * Get all sprint by project list.
   *
   * @param projectId the project id
   * @return the list
   */
  @GetMapping("/project/{projectId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Sprint> getAllSprintByProject(@PathVariable Long projectId) {
    return sprintService.getAllByProject(projectId);
  }

  /**
   * Finish sprint sprint.
   *
   * @param sprintId the sprint id
   * @return the sprint
   */
  @PutMapping("/finish/{sprintId}")
  @PreAuthorize("hasAuthority('TEAM_LEAD')")
  @ResponseStatus(HttpStatus.OK)
  public Sprint finishSprint(@PathVariable Long sprintId) {
    return sprintService.finishSprint(sprintId);
  }

  /**
   * Get current sprint sprint.
   *
   * @param projectId the project id
   * @return the sprint
   */
  @GetMapping("/getCurrentForProject/{projectId}")
  @ResponseStatus(HttpStatus.OK)
  public Sprint getCurrentSprint(@PathVariable Long projectId) {
    return sprintService.getCurrentSprint(projectId);
  }
}
