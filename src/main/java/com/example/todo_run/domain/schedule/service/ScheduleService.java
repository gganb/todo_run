package com.example.todo_run.domain.schedule.service;

import com.example.todo_run.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.todo_run.domain.schedule.dto.response.ScheduleCommentListResponseDto;
import com.example.todo_run.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.todo_run.domain.schedule.dto.response.UpdateScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedule(Long writerId);

    ScheduleCommentListResponseDto findSchedule(Long scheduleId);

    UpdateScheduleResponseDto updateSchedule(Long scheduleId, UpdateScheduleRequestDto requestDto);

    void deleteSchedule(Long writerId, Long scheduleId);

}
