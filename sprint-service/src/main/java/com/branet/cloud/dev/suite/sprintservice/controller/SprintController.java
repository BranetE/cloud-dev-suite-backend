package com.branet.cloud.dev.suite.sprintservice.controller;

import com.branet.cloud.dev.suite.sprintservice.dto.CreateSprintRequest;
import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import com.branet.cloud.dev.suite.sprintservice.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprint")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @PostMapping("/{sprintId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Sprint getSprint(@PathVariable Long sprintId){
        return sprintService.getSprint(sprintId);
    }

    @PostMapping("/start")
    @PreAuthorize("hasAuthority('TEAM_LEAD')")
    @ResponseStatus(HttpStatus.CREATED)
    public Sprint createSprint(@RequestBody CreateSprintRequest createSprintRequest){
        return sprintService.createSprint(createSprintRequest);
    }
    @GetMapping("/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Sprint> getAllSprintByProject(@PathVariable Long projectId){
        return sprintService.getAllByProject(projectId);
    }

    @PutMapping("/finish/{sprintId}")
    @PreAuthorize("hasAuthority('TEAM_LEAD')")
    @ResponseStatus(HttpStatus.OK)
    public Sprint finishSprint(@PathVariable Long sprintId){
        return sprintService.finishSprint(sprintId);
    }

    @GetMapping("/getCurrentForProject/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public Sprint getCurrentSprint(@PathVariable Long projectId){
        return sprintService.getCurrentSprint(projectId);
    }
}
