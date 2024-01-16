package com.branet.cloud.dev.suite.sprintservice.controller;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.dto.FinishTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Task;
import com.branet.cloud.dev.suite.sprintservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('TEAM_LEAD')")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody CreateTaskRequest createTaskRequest, @PathVariable Long sprintId) {
        return taskService.createTask(createTaskRequest, sprintId);
    }

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable Long taskId){
        return taskService.getTask(taskId);
    }

    @PatchMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatus(@PathVariable Long taskId, @RequestParam("status") String status, Long employeeId){
        taskService.updateStatus(taskId, employeeId, status);
    }

    @PutMapping("/finishTask/{taskId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('DESIGNER')")
    @ResponseStatus(HttpStatus.OK)
    public Task finishTask(@RequestBody FinishTaskRequest finishTaskRequest, @PathVariable Long taskId, Long employeeId) {
        return taskService.finishTask(taskId, employeeId, finishTaskRequest);
    }

    @GetMapping("/getBySprint/{sprintId}/open")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllOpenTasksBySprintId(@PathVariable Long sprintId) {
        return taskService.getOpenTasksBySprint(sprintId);
    }

    @GetMapping("/getByEmployee/{employeeId}")
    @PreAuthorize("hasAuthority('TEAM_LEAD') or authentication.principal.id == #employeeId")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllByEmployeeId(@PathVariable Long employeeId) {
        return taskService.getAllTasksByEmployee(employeeId);
    }

    @GetMapping("/getByShift/{shiftId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('DESIGNER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllByShiftId(@PathVariable Long shiftId){
        return taskService.getAllTasksByShiftId(shiftId);
    }
}
