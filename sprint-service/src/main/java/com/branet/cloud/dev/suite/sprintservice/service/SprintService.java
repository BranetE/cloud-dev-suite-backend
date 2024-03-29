package com.branet.cloud.dev.suite.sprintservice.service;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateSprintRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import com.branet.cloud.dev.suite.sprintservice.model.SprintStatus;
import com.branet.cloud.dev.suite.sprintservice.repository.SprintRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;

    public Sprint createSprint(CreateSprintRequest createSprintRequest){
        Sprint sprint = new Sprint();
        sprint.setStatus(SprintStatus.OPEN);
        sprint.setTitle(createSprintRequest.title());
        sprint.setProjectId(createSprintRequest.projectId());
        sprint.setStartDate(LocalDate.now());
        sprint.setFinishDate(LocalDate.parse(createSprintRequest.finishDate()));

        return sprintRepository.save(sprint);
    }

    public Sprint getSprint(Long sprintId){
        return sprintRepository.findById(sprintId).orElseThrow(() -> new EntityNotFoundException());
    }

    public Sprint finishSprint(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new EntityNotFoundException());

        sprint.setStatus(SprintStatus.CLOSED);
        sprint.setFinishDate(LocalDate.now());
        return sprintRepository.save(sprint);
    }

    public Sprint getCurrentSprint(Long projectId){
        return sprintRepository.findCurrentByProjectId(projectId).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Sprint> getAllByProject(Long projectId) {
        return sprintRepository.findAllByProjectId(projectId);
    }
}
