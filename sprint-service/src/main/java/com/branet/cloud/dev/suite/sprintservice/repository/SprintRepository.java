package com.branet.cloud.dev.suite.sprintservice.repository;

import com.branet.cloud.dev.suite.sprintservice.model.Sprint;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** The interface Sprint repository. */
@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
  /**
   * Find current by project id optional.
   *
   * @param projectId the project id
   * @return the optional
   */
  @Query(
      value =
          "SELECT * FROM sprints AS s WHERE s.status='OPEN' AND s.endDate=null AND s.projectId=:projectId",
      nativeQuery = true)
  Optional<Sprint> findCurrentByProjectId(@Param("projectId") Long projectId);

  /**
   * Find all by project id list.
   *
   * @param projectId the project id
   * @return the list
   */
  List<Sprint> findAllByProjectId(Long projectId);
}
