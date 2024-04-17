package com.branet.cloud.dev.suite.shiftservice.service;

import com.branet.cloud.dev.suite.shiftservice.dto.AddShiftRequest;
import com.branet.cloud.dev.suite.shiftservice.model.Shift;
import com.branet.cloud.dev.suite.shiftservice.model.ShiftType;
import com.branet.cloud.dev.suite.shiftservice.repository.ShiftRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Shift service. */
@Service
@Transactional
@RequiredArgsConstructor
public class ShiftService {

  private final ShiftRepository shiftRepository;

  /**
   * Create new shift for employee shift.
   *
   * @param addShiftRequest the add shift request
   * @param employeeId the employee id
   * @return the shift
   */
  public Shift createNewShiftForEmployee(AddShiftRequest addShiftRequest, Long employeeId) {
    if (shiftRepository.findCurrentShift(employeeId).isPresent()) {
      throw new EntityExistsException("There is other opened shift for current employee");
    }

    Shift shift = new Shift();
    shift.setEmployeeId(employeeId);
    shift.setShiftType(ShiftType.valueOf(addShiftRequest.shiftType()));
    shift.setStartTime(LocalDateTime.now());

    return shiftRepository.save(shift);
  }

  /**
   * Get all shifts by employee list.
   *
   * @param employeeId the employee id
   * @return the list
   */
  public List<Shift> getAllShiftsByEmployee(Long employeeId) {
    return shiftRepository.findAllByEmployeeId(employeeId);
  }

  /**
   * Get current shift by employee id shift.
   *
   * @param employeeId the employee id
   * @return the shift
   */
  public Shift getCurrentShiftByEmployeeId(Long employeeId) {
    return shiftRepository
        .findCurrentShift(employeeId)
        .orElseThrow(() -> new EntityNotFoundException());
  }

  /**
   * Get shift shift.
   *
   * @param shiftId the shift id
   * @return the shift
   */
  public Shift getShift(Long shiftId) {
    return shiftRepository.findById(shiftId).orElseThrow(() -> new EntityNotFoundException());
  }

  /**
   * Finish current shift.
   *
   * @param employeeId the employee id
   */
  public void finishCurrentShift(Long employeeId) {
    Shift currentShift =
        shiftRepository
            .findCurrentShift(employeeId)
            .orElseThrow(() -> new EntityNotFoundException());
    shiftRepository.setEndTime(LocalDateTime.now(), currentShift.getId());
  }

  /**
   * Add task to current shift shift.
   *
   * @param employeeId the employee id
   * @param taskId the task id
   * @return the shift
   */
  public Shift addTaskToCurrentShift(Long employeeId, Long taskId) {
    Shift currentShift =
        shiftRepository
            .findCurrentShift(employeeId)
            .orElseThrow(() -> new EntityNotFoundException());
    currentShift.addTask(taskId);
    return shiftRepository.save(currentShift);
  }

  /**
   * Remove task from shift shift.
   *
   * @param shiftId the shift id
   * @param taskId the task id
   * @return the shift
   */
  public Shift removeTaskFromShift(Long shiftId, Long taskId) {
    Shift shift =
        shiftRepository.findById(shiftId).orElseThrow(() -> new EntityNotFoundException());
    shift.removeTask(taskId);
    return shiftRepository.save(shift);
  }
}
