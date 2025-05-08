package com.example.todo_run.domain.schedule.repository;

import com.example.todo_run.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s LEFT JOIN FETCH s.commentList WHERE s.writerId = :writerId ORDER BY s.createdAt DESC")
    List<Schedule> findAllByWriterIdOrderByCreatedAtDesc(Long writerId);
}
