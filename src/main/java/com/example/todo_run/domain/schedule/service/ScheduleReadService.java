package com.example.todo_run.domain.schedule.service;

import com.example.todo_run.domain.schedule.entity.Schedule;

public interface ScheduleReadService {
    Schedule findByIdOrElseThrow(Long scheduleId);
}
