package com.branet.cloud.dev.suite.sprintservice.repository;

import com.branet.cloud.dev.suite.sprintservice.model.Task;
import com.branet.cloud.dev.suite.sprintservice.model.TaskStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** The interface Task repository. */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  /**
   * Find all open by sprint id list.
   *
   * @param sprintId the sprint id
   * @return the list
   */
  @Query(
      value = "SELECT * FROM tasks AS t WHERE t.sprint_id=:sprintId AND status='OPEN'",
      nativeQuery = true)
  List<Task> findAllOpenBySprintId(@Param("sprintId") Long sprintId);

  /**
   * Find all by employee id list.
   *
   * @param employeeId the employee id
   * @return the list
   */
  List<Task> findAllByEmployeeId(Long employeeId);

  /**
   * Find all by shift id list.
   *
   * @param shiftId the shift id
   * @return the list
   */
  List<Task> findAllByShiftId(Long shiftId);

  /**
   * Update status by id.
   *
   * @param taskId the task id
   * @param status the status
   */
  @Modifying
  @Query(value = "UPDATE tasks AS s SET s.status=:status WHERE s.id=:taskId", nativeQuery = true)
  void updateStatusById(@Param("taskId") Long taskId, @Param("status") TaskStatus status);

  //    @Modifying
  //    @Query(value = "UPDATE tasks AS s SET s.comment=:comment WHERE s.id=:taskId",
  //            nativeQuery = true)
  //    void updateCommentById(@Param("taskId") Long taskId, @Param("comment") String comment);
}
