package com.branet.cloud.dev.suite.sprintservice.service;

import com.branet.cloud.dev.suite.sprintservice.client.ShiftClient;
import com.branet.cloud.dev.suite.sprintservice.client.model.Shift;
import com.branet.cloud.dev.suite.sprintservice.dto.CreateTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.dto.FinishTaskRequest;
import com.branet.cloud.dev.suite.sprintservice.dto.mapper.TaskMapper;
import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import com.branet.cloud.dev.suite.sprintservice.model.Task;
import com.branet.cloud.dev.suite.sprintservice.model.TaskStatus;
import com.branet.cloud.dev.suite.sprintservice.repository.SprintRepository;
import com.branet.cloud.dev.suite.sprintservice.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final ShiftClient shiftClient;
    private final TaskMapper taskMapper;

    public Task createTask(CreateTaskRequest createTaskRequest, Long sprintId){
        Task task = taskMapper.dtoToEntity(createTaskRequest);
        task.setStatus(TaskStatus.OPEN);

        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new EntityNotFoundException());
        task.setSprint(sprint);

        return taskRepository.save(task);
    }

    public Task getTask(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException());

    }

    public void updateStatus(Long taskId, Long employeeId, String status){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException());
        if(status.equalsIgnoreCase("IN_PROGRESS")) {
            Shift currentShift = shiftClient.getCurrentShiftForEmployee(employeeId);
            task.setEmployeeId(employeeId);
            task.setShiftId(currentShift.id());
            taskRepository.updateStatusById(task.getId(), TaskStatus.valueOf(status));
        }
        if(task.getStatus().equals(TaskStatus.DONE)){
            shiftClient.removeTaskFromShift(task.getId(), task.getShiftId());
            taskRepository.updateStatusById(task.getId(), TaskStatus.valueOf(status));
        }
    }

    public Task finishTask(Long taskId, Long employeeId, FinishTaskRequest finishTaskRequest){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException());
        Shift currentShift = shiftClient.getCurrentShiftForEmployee(employeeId);
        task.setStatus(TaskStatus.DONE);
        task.setComment(finishTaskRequest.comment());
        task.setFinishTime(LocalDateTime.now());
        task.setShiftId(currentShift.id());
        shiftClient.addFinishedTaskToShift(task.getId(), task.getEmployeeId());
        return taskRepository.save(task);
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
