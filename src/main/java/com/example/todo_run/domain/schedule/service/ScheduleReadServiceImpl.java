package com.example.todo_run.domain.schedule.service;

import com.example.todo_run.domain.schedule.entity.Schedule;
import com.example.todo_run.domain.schedule.exception.ScheduleException;
import com.example.todo_run.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.todo_run.domain.schedule.exception.ScheduleError.SCHEDULE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ScheduleReadServiceImpl implements ScheduleReadService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule findByIdOrElseThrow(Long scheduleId) {

        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleException(SCHEDULE_NOT_FOUND)
        );
    }
}
