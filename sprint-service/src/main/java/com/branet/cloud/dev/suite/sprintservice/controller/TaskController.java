package com.branet.cloud.dev.suite.sprintservice.controller;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Task;
import com.branet.cloud.dev.suite.sprintservice.security.util.UserDetailsImpl;
import com.branet.cloud.dev.suite.sprintservice.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/** The type Task controller. */
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
  private final TaskService taskService;

  /**
   * Create task task.
   *
   * @param createTaskRequest the create task request
   * @return the task
   */
  @PostMapping("/create")
  @PreAuthorize("hasAuthority('TEAM_LEAD')")
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@RequestBody CreateTaskRequest createTaskRequest) {
    return taskService.createTask(createTaskRequest);
  }

  /**
   * Get task task.
   *
   * @param taskId the task id
   * @return the task
   */
  @GetMapping("/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  public Task getTask(@PathVariable Long taskId) {
    return taskService.getTask(taskId);
  }

  /**
   * Change status.
   *
   * @param taskId the task id
   * @param status the status
   * @param userDetails the user details
   */
  @PutMapping("/{taskId}")
  //    @PreAuthorize("authentication.principal.id == #userDetails.id")
  @ResponseStatus(HttpStatus.OK)
  public void changeStatus(
      @PathVariable Long taskId,
      @RequestParam("status") String status,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    taskService.updateStatus(taskId, userDetails.getId(), status);
  }

  //    @PutMapping("/finishTask/{taskId}")
  //    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('DESIGNER')")
  //    @ResponseStatus(HttpStatus.OK)
  //    public Task finishTask(@RequestBody FinishTaskRequest finishTaskRequest, @PathVariable Long
  // taskId, Long employeeId) {
  //        return taskService.finishTask(taskId, employeeId, finishTaskRequest);
  //    }

  /**
   * Gets all open tasks by sprint id.
   *
   * @param sprintId the sprint id
   * @return the all open tasks by sprint id
   */
  @GetMapping("/getBySprint/{sprintId}/open")
  @ResponseStatus(HttpStatus.OK)
  public List<Task> getAllOpenTasksBySprintId(@PathVariable Long sprintId) {
    return taskService.getOpenTasksBySprint(sprintId);
  }

  /**
   * Gets all by employee id.
   *
   * @param employeeId the employee id
   * @return the all by employee id
   */
  @GetMapping("/getByEmployee/{employeeId}")
  @PreAuthorize("hasAuthority('TEAM_LEAD') or authentication.principal.id == #employeeId")
  @ResponseStatus(HttpStatus.OK)
  public List<Task> getAllByEmployeeId(@PathVariable Long employeeId) {
    return taskService.getAllTasksByEmployee(employeeId);
  }

  /**
   * Get all by shift id list.
   *
   * @param shiftId the shift id
   * @return the list
   */
  @GetMapping("/getByShift/{shiftId}")
  @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('DESIGNER')")
  @ResponseStatus(HttpStatus.OK)
  public List<Task> getAllByShiftId(@PathVariable Long shiftId) {
    return taskService.getAllTasksByShiftId(shiftId);
  }
}
