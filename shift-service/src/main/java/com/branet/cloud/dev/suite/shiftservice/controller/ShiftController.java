package com.branet.cloud.dev.suite.shiftservice.controller;

import com.branet.cloud.dev.suite.shiftservice.dto.AddShiftRequest;
import com.branet.cloud.dev.suite.shiftservice.model.Shift;
import com.branet.cloud.dev.suite.shiftservice.security.util.UserDetailsImpl;
import com.branet.cloud.dev.suite.shiftservice.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** The type Shift controller. */
@RestController
@RequestMapping("/shift")
@RequiredArgsConstructor
public class ShiftController {
  private final ShiftService shiftService;

  /**
   * Get shift shift.
   *
   * @param shiftId the shift id
   * @return the shift
   */
  @GetMapping("/{shiftId}")
  @ResponseStatus(HttpStatus.OK)
  public Shift getShift(@PathVariable Long shiftId) {
    return shiftService.getShift(shiftId);
  }

  /**
   * Gets current shift for employee.
   *
   * @param employeeid the employeeid
   * @return the current shift for employee
   */
  @GetMapping("/getCurrent")
  @ResponseStatus(HttpStatus.OK)
  public Shift getCurrentShiftForEmployee(@RequestParam("employeeId") Long employeeid) {
    return shiftService.getCurrentShiftByEmployeeId(employeeid);
  }

  /**
   * Get all shifts by employee list.
   *
   * @param employeeId the employee id
   * @return the list
   */
  @GetMapping("/getAllByEmployee/{employeeId}")
  @PreAuthorize("authentication.principal.id == #employeeId or hasAuthority('TEAM_LEAD')")
  @ResponseStatus(HttpStatus.OK)
  public List<Shift> getAllShiftsByEmployee(@PathVariable Long employeeId) {
    return shiftService.getAllShiftsByEmployee(employeeId);
  }

  /**
   * Start new shift for employee shift.
   *
   * @param employee the employee
   * @param addShiftRequest the add shift request
   * @return the shift
   */
  @PostMapping("/start")
  @ResponseStatus(HttpStatus.CREATED)
  public Shift startNewShiftForEmployee(
      @AuthenticationPrincipal UserDetailsImpl employee,
      @RequestBody AddShiftRequest addShiftRequest) {
    return shiftService.createNewShiftForEmployee(addShiftRequest, employee.getId());
  }

  /**
   * End shift for employee.
   *
   * @param employeeId the employee id
   */
  @PutMapping("/end/{employeeId}")
  @PreAuthorize("authentication.principal.id == #employeeId or hasAuthority('TEAM_LEAD')")
  @ResponseStatus(HttpStatus.OK)
  public void endShiftForEmployee(@PathVariable("employeeId") Long employeeId) {
    shiftService.finishCurrentShift(employeeId);
  }

  /**
   * Add task for current shift shift.
   *
   * @param taskId the task id
   * @param employeeId the employee id
   * @return the shift
   */
  @PutMapping("/addTask")
  //    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('DESIGNER')")
  @ResponseStatus(HttpStatus.OK)
  public Shift addTaskForCurrentShift(
      @RequestParam("taskId") Long taskId, @RequestParam("employeeId") Long employeeId) {
    return shiftService.addTaskToCurrentShift(employeeId, taskId);
  }

  //    @PatchMapping("/removeTask")
  //    @ResponseStatus(HttpStatus.OK)
  //    public Shift removeTaskForCurrentShift(@RequestParam("taskId") Long taskId,
  // @RequestParam("employeeId") Long employeeId) {
  //        return shiftService.removeTaskFromCurrentShift(employeeId, taskId);
  //    }

  /**
   * Remove task for shift shift.
   *
   * @param taskId the task id
   * @param shiftId the shift id
   * @return the shift
   */
  @PatchMapping("{shiftId}/removeTask")
  @ResponseStatus(HttpStatus.OK)
  public Shift removeTaskForShift(
      @RequestParam("taskId") Long taskId, @PathVariable("shiftId") Long shiftId) {
    return shiftService.removeTaskFromShift(shiftId, taskId);
  }
}
