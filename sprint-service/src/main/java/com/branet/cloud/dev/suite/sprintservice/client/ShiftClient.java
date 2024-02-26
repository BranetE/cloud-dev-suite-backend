package com.branet.cloud.dev.suite.sprintservice.client;

import com.branet.cloud.dev.suite.sprintservice.client.model.Shift;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "shift-service", url = "http://localhost:8070")
public interface ShiftClient {

    @GetMapping("/shift/getCurrent")
    Shift getCurrentShiftForEmployee(@RequestParam("employeeId") Long employeeId);

    @PutMapping("/shift/addTask")
    void addFinishedTaskToShift(@RequestParam("taskId") Long taskId, @RequestParam("employeeId") Long employeeId);

    @PutMapping("/shift/{shiftId}/removeTask")
    void removeTaskFromShift(@RequestParam("taskId") Long taskId, @PathVariable("shiftId") Long shiftId);
}
