package com.branet.cloud.dev.suite.sprintservice.repository;

import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
    @Query(value = "SELECT * FROM sprints AS s WHERE s.status='OPEN' AND s.endDate=null AND s.projectId=:projectId",
            nativeQuery = true)
    Optional<Sprint> findCurrentByProjectId(@Param("projectId") Long projectId);

    List<Sprint> findAllByProjectId(Long projectId);
}
