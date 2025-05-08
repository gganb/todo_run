package com.example.todo_run.domain.schedule.controller;

import com.example.todo_run.common.response.ApiResponse;
import com.example.todo_run.common.response.MessageResponse;
import com.example.todo_run.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.response.SaveScheduleResponseDto;
import com.example.todo_run.domain.schedule.dto.response.ScheduleCommentListResponseDto;
import com.example.todo_run.domain.schedule.dto.response.ScheduleResponseDto;
import com.example.todo_run.domain.schedule.dto.response.UpdateScheduleResponseDto;
import com.example.todo_run.domain.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 API
     * 요청 예시 :
     * {
     * "writerId" : 1,
     * "title" : "제목",
     * "contents" : "내용"
     * }
     */
    @PostMapping
    public ResponseEntity<ApiResponse<SaveScheduleResponseDto>> saveSchedule(
            @Valid @RequestBody SaveScheduleRequestDto requestDto
    ) {
        SaveScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return ResponseEntity.status(201).body(ApiResponse.of("일정이 생성되었습니다.", responseDto));
    }

    /**
     * 일정 전체 조회 API
     * 특정 작성자가 작성한 일정을 전부 조회합니다.
     * <p>
     * 엔드포인트 예시
     * GET /api/schedules?writerId = 1
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ScheduleResponseDto>>> findAllSchedule(
            @RequestParam Long writerId
    ) {
        List<ScheduleResponseDto> scheduleList = scheduleService.findAllSchedule(writerId);
        return ResponseEntity.ok().body(ApiResponse.of("일정을 작성자 기준으로 조회합니다.", scheduleList));
    }

    /**
     * 일정 단건 조회 API
     * 일정 id로 단건 조회합니다.
     * <p>
     * 엔드포인트 예시:
     * GET /api/schedules/1
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleCommentListResponseDto>> findSchedule(
            @PathVariable Long scheduleId
    ) {
        ScheduleCommentListResponseDto responseDto = scheduleService.findSchedule(scheduleId);
        return ResponseEntity.ok().body(ApiResponse.of("일정을 단건 조회합니다.", responseDto));
    }

    /**
     * 일정 수정 API
     * 작성자가 본인의 일정을 수정합니다.
     * <p>
     * 엔드포인트 예시:
     * PUT /api/schedules/2
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<UpdateScheduleResponseDto>> updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequestDto requestDto
    ) {
        UpdateScheduleResponseDto responseDto = scheduleService.updateSchedule(scheduleId, requestDto);
        return ResponseEntity.ok().body(ApiResponse.of("일정이 수정되었습니다.", responseDto));
    }

    /**
     * 일정 삭제 API
     * 작성자가 본인의 일정을 삭제합니다.
     * <p>
     * 엔드포인트 예시:
     * DELETE /api/schedules/1/2
     */
    @DeleteMapping("/{writerId}/{scheduleId}")
    public ResponseEntity<MessageResponse> deleteSchedule(
            @PathVariable Long writerId,
            @PathVariable Long scheduleId
    ) {
        scheduleService.deleteSchedule(writerId, scheduleId);
        return ResponseEntity.ok().body(MessageResponse.from("일정이 삭제되었습니다."));
    }
}
