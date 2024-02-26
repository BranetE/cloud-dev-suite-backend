package com.branet.cloud.dev.suite.sprintservice.service;

import com.branet.cloud.dev.suite.sprintservice.client.ShiftClient;
import com.branet.cloud.dev.suite.sprintservice.client.model.Shift;
import com.branet.cloud.dev.suite.sprintservice.dto.CreateTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import com.branet.cloud.dev.suite.sprintservice.model.Task;
import com.branet.cloud.dev.suite.sprintservice.model.TaskStatus;
import com.branet.cloud.dev.suite.sprintservice.repository.SprintRepository;
import com.branet.cloud.dev.suite.sprintservice.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final ShiftClient shiftClient;

    public Task createTask(CreateTaskRequest createTaskRequest){
        Task task = new Task();
        task.setTitle(createTaskRequest.title());
        task.setDescription(createTaskRequest.description());

        task.setStatus(TaskStatus.OPEN);

        Sprint sprint = sprintRepository.findById(createTaskRequest.sprintId()).orElseThrow(() -> new EntityNotFoundException());
        task.setSprint(sprint);

        return taskRepository.save(task);
    }

    public Task getTask(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException());

    }

    public void updateStatus(Long taskId, Long employeeId, String status){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException());

        if(task.getStatus().equals(TaskStatus.DONE)){
            shiftClient.removeTaskFromShift(task.getId(), task.getShiftId());
            taskRepository.updateStatusById(task.getId(), TaskStatus.valueOf(status));
        }

        if(status.equalsIgnoreCase("IN_PROGRESS")) {
            task.setEmployeeId(employeeId);
            task.setStatus(TaskStatus.IN_PROGRESS);
            taskRepository.save(task);
        }else if(status.equalsIgnoreCase("OPEN")){
            task.setEmployeeId(null);
            task.setStatus(TaskStatus.valueOf(status));
            taskRepository.save(task);
        }else if(status.equalsIgnoreCase("DONE")){
            task.setEmployeeId(employeeId);
            task.setStatus(TaskStatus.valueOf(status));
            Shift currentShift = shiftClient.getCurrentShiftForEmployee(employeeId);
            task.setStatus(TaskStatus.DONE);
            task.setFinishTime(LocalDateTime.now());
            task.setShiftId(currentShift.id());
            shiftClient.addFinishedTaskToShift(task.getId(), task.getEmployeeId());
            taskRepository.save(task);
        }

    }

    public List<Task> getOpenTasksBySprint(Long sprintId){
        return taskRepository.findAllOpenBySprintId(sprintId);
    }

    public List<Task> getAllTasksByEmployee(Long employeeId){
        return taskRepository.findAllByEmployeeId(employeeId);
    }

    public List<Task> getAllTasksByShiftId(Long shiftId){
        return taskRepository.findAllByShiftId(shiftId);
    }
}
