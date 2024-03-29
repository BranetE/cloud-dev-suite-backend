package com.branet.cloud.dev.suite.shiftservice.service;

import com.branet.cloud.dev.suite.shiftservice.dto.AddShiftRequest;
import com.branet.cloud.dev.suite.shiftservice.model.Shift;
import com.branet.cloud.dev.suite.shiftservice.model.ShiftType;
import com.branet.cloud.dev.suite.shiftservice.repository.ShiftRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public Shift createNewShiftForEmployee(AddShiftRequest addShiftRequest, Long employeeId){
        if(shiftRepository.findCurrentShift(employeeId).isPresent()) {
            throw new EntityExistsException("There is other opened shift for current employee");
        }

        Shift shift = new Shift();
        shift.setEmployeeId(employeeId);
        shift.setShiftType(ShiftType.valueOf(addShiftRequest.shiftType()));
        shift.setStartTime(LocalDateTime.now());

        return shiftRepository.save(shift);
    }

    public List<Shift> getAllShiftsByEmployee(Long employeeId){
        return shiftRepository.findAllByEmployeeId(employeeId);
    }

    public Shift getCurrentShiftByEmployeeId(Long employeeId){
        return shiftRepository.findCurrentShift(employeeId).orElseThrow(() -> new EntityNotFoundException());
    }

    public Shift getShift(Long shiftId){
        return shiftRepository.findById(shiftId).orElseThrow(() -> new EntityNotFoundException());
    }

    public void finishCurrentShift(Long employeeId) {
        Shift currentShift = shiftRepository.findCurrentShift(employeeId).orElseThrow(() -> new EntityNotFoundException());
        shiftRepository.setEndTime(LocalDateTime.now(), currentShift.getId());
    }

    public Shift addTaskToCurrentShift(Long employeeId, Long taskId){
        Shift currentShift = shiftRepository.findCurrentShift(employeeId).orElseThrow(() -> new EntityNotFoundException());
        currentShift.addTask(taskId);
        return shiftRepository.save(currentShift);
    }

    public Shift removeTaskFromShift(Long shiftId, Long taskId){
        Shift shift = shiftRepository.findById(shiftId).orElseThrow(() -> new EntityNotFoundException());
        shift.removeTask(taskId);
        return shiftRepository.save(shift);
    }
}
