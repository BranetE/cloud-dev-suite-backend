package com.branet.cloud.dev.suite.sprintservice.repository;

import com.branet.cloud.dev.suite.sprintservice.model.Task;
import com.branet.cloud.dev.suite.sprintservice.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM tasks AS t WHERE t.sprintId=:sprintId AND status='OPEN'",
            nativeQuery = true)
    List<Task> findAllOpenBySprintId(@Param("sprintId") Long sprintId);

    List<Task> findAllByEmployeeId(Long employeeId);

    List<Task> findAllByShiftId(Long shiftId);


    @Modifying
    @Query(value = "UPDATE tasks AS s SET s.status=:status WHERE s.id=:taskId",
            nativeQuery = true)
    void updateStatusById(@Param("taskId") Long taskId, @Param("status") TaskStatus status);

//    @Modifying
//    @Query(value = "UPDATE tasks AS s SET s.comment=:comment WHERE s.id=:taskId",
//            nativeQuery = true)
//    void updateCommentById(@Param("taskId") Long taskId, @Param("comment") String comment);
}
