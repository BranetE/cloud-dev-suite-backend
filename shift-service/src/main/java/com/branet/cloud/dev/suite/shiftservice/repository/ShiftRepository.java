package com.branet.cloud.dev.suite.shiftservice.repository;

import com.branet.cloud.dev.suite.shiftservice.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findAllByEmployeeId(@Param("userId") Long userId);

    @Query(value = "SELECT * from shifts AS s WHERE s.endTime=null AND employeeId=:employeeId ORDER BY id DESC LIMIT 1",
    nativeQuery = true)
    Optional<Shift> findCurrentShift(@Param("employeeId") Long employeeId);

    @Query(value = "UPDATE shifts AS s SET s.endTime=:endTime WHERE s.id=:id",
    nativeQuery = true)
    @Modifying
    void setEndTime(@Param("endTime") LocalDateTime endTime, @Param("id") Long id);

}
