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

@RestController
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftService shiftService;

    @GetMapping("/getCurrent")
    @ResponseStatus(HttpStatus.OK)
    public Shift getCurrentShiftForEmployee(@AuthenticationPrincipal UserDetailsImpl employee){
        return shiftService.getCurrentShiftByEmployeeId(employee.getId());
    }

    @PostMapping("/startShift")
    @ResponseStatus(HttpStatus.CREATED)
    public Shift startNewShiftForEmployee(@AuthenticationPrincipal UserDetailsImpl employee, @RequestBody AddShiftRequest addShiftRequest){
        return shiftService.createNewShiftForEmployee(addShiftRequest, employee.getId());
    }

    @PatchMapping("/endShift/{employeeId}")
    @PreAuthorize("principal.id = #employeeId or hasAuthority('TEAM_LEAD')")
    @ResponseStatus(HttpStatus.OK)
    public void endShiftForEmployee(@PathVariable("employeeId") Long employeeId) {
        shiftService.finishCurrentShift(employeeId);
    }

    @PatchMapping("/addTask")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('DESIGNER')")
    @ResponseStatus(HttpStatus.OK)
    public Shift addTaskForCurrentShift(@RequestParam("taskId") Long taskId, @AuthenticationPrincipal UserDetailsImpl employee) {
        return shiftService.addTaskToCurrentShift(employee.getId(), taskId);
    }

//    @PatchMapping("/removeTask")
//    @ResponseStatus(HttpStatus.OK)
//    public Shift removeTaskForCurrentShift(@RequestParam("taskId") Long taskId, @RequestParam("employeeId") Long employeeId) {
//        return shiftService.removeTaskFromCurrentShift(employeeId, taskId);
//    }

    @PatchMapping("{shiftId}/removeTask")
    @ResponseStatus(HttpStatus.OK)
    public Shift removeTaskForShift(@RequestParam("taskId") Long taskId, @PathVariable("shiftId") Long shiftId) {
        return shiftService.removeTaskFromShift(shiftId, taskId);
    }
}
