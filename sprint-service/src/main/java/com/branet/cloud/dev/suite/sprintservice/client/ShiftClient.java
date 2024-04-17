package com.branet.cloud.dev.suite.sprintservice.client;

import com.branet.cloud.dev.suite.sprintservice.client.model.Shift;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** The interface Shift client. */
@FeignClient(name = "shift-service", url = "http://localhost:8070")
public interface ShiftClient {

  /**
   * Gets current shift for employee.
   *
   * @param employeeId the employee id
   * @return the current shift for employee
   */
  @GetMapping("/shift/getCurrent")
  Shift getCurrentShiftForEmployee(@RequestParam("employeeId") Long employeeId);

  /**
   * Add finished task to shift.
   *
   * @param taskId the task id
   * @param employeeId the employee id
   */
  @PutMapping("/shift/addTask")
  void addFinishedTaskToShift(
      @RequestParam("taskId") Long taskId, @RequestParam("employeeId") Long employeeId);

  /**
   * Remove task from shift.
   *
   * @param taskId the task id
   * @param shiftId the shift id
   */
  @PutMapping("/shift/{shiftId}/removeTask")
  void removeTaskFromShift(
      @RequestParam("taskId") Long taskId, @PathVariable("shiftId") Long shiftId);
}
