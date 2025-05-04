package com.example.todo_run.domain.schedule.repository;

import com.example.todo_run.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByWriterIdOrderByCreatedAtDesc(Long writerId);

    Optional<Schedule> findScheduleByWriterId(Long writerId);
}
