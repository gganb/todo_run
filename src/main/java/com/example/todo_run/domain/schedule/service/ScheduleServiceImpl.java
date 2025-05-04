package com.example.todo_run.domain.schedule.service;

import com.example.todo_run.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.todo_run.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.todo_run.domain.schedule.dto.response.UpdateScheduleResponseDto;
import com.example.todo_run.domain.schedule.entity.Schedule;
import com.example.todo_run.domain.schedule.exception.ScheduleException;
import com.example.todo_run.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.todo_run.domain.schedule.exception.ScheduleError.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    // 일정이 생기고 댓글이 달릴 수 있음..
    @Override
    @Transactional
    public SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto) {
        Schedule schedule = Schedule.toEntity(requestDto);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return SaveScheduleResponseDto.from(savedSchedule);
    }

    // 일정 전체 조회
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedule(Long writerId) {
        List<Schedule> scheduleList = scheduleRepository.findAllByWriterIdOrderByCreatedAtDesc(writerId);

        if (scheduleList.isEmpty()) {
            throw new ScheduleException(SCHEDULE_NOT_FOUND);
        }

        return scheduleList.stream().map(ScheduleResponseDto::from).toList();
    }

    // 일정 단건 조회
    @Override
    @Transactional(readOnly = true)
    public ScheduleResponseDto findSchedule(Long scheduleId) {
        Schedule findSchedule = findByIdOrElseThrow(scheduleId);

        return ScheduleResponseDto.from(findSchedule);
    }

    // 일정 수정
    @Override
    @Transactional
    public UpdateScheduleResponseDto updateSchedule(Long writerId, Long scheduleId, UpdateScheduleRequestDto requestDto) {
        Schedule findSchedule = findByIdOrElseThrow(scheduleId);

        if (!findSchedule.getWriterId().equals(writerId)) {
            throw new ScheduleException(SCHEDULE_MODIFY_NOT_ALLOWED);
        }

        findSchedule.updateSchedule(requestDto);

        return UpdateScheduleResponseDto.from(findSchedule);
    }

    // 일정 삭제
    @Override
    @Transactional
    public void deleteSchedule(Long writerId, Long scheduleId) {
        Schedule findSchedule = findByIdOrElseThrow(scheduleId);

        if (!findSchedule.getWriterId().equals(writerId)) {
            throw new ScheduleException(SCHEDULE_DELETE_NOT_ALLOWED);
        }
        scheduleRepository.delete(findSchedule);
    }

    @Override
    public Schedule findByIdOrElseThrow(Long scheduleId) {

        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleException(SCHEDULE_NOT_FOUND)
        );
    }
}
