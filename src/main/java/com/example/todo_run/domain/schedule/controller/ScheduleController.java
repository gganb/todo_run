package com.example.todo_run.domain.schedule.controller;

import com.example.todo_run.common.response.ApiResponse;
import com.example.todo_run.common.response.MessageResponse;
import com.example.todo_run.domain.schedule.dto.request.SaveScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.example.todo_run.domain.schedule.dto.response.SaveScheduleResponseDto;
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
     *     "writerId" : 1,
     *     "title" : "제목",
     *     "contents" : "내용"
     * }
     */
    @PostMapping
    public ResponseEntity<ApiResponse<SaveScheduleResponseDto>> saveSchedule(
            @Valid @RequestBody SaveScheduleRequestDto requestDto
    ) {
        log.info("controller");
        SaveScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return ResponseEntity.status(201).body(ApiResponse.of("일정이 생성되었습니다.", responseDto));
    }

    /**
     * 특정 작성자가 작성한 일정 전체 조회
     * 엔드포인트 예시
     * GET /api/schedules/writerId = 1
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ScheduleResponseDto>>> findAllSchedule(
            @NotNull @RequestParam Long writerId
    ) {
        List<ScheduleResponseDto> scheduleList = scheduleService.findAllSchedule(writerId);
        return ResponseEntity.ok().body(ApiResponse.of("일정을 작성자 기준으로 조회합니다.", scheduleList));
    }

    // 일정 id로 단건 조회
    // 일정 id로 검색하는게 맞는지.. 작성자 id를 추가로 받아야하나? 작성자. 일정 id를 받아야하나
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleResponseDto>> findSchedule(
            @NotNull @PathVariable Long scheduleId
    ) {
        ScheduleResponseDto responseDto = scheduleService.findSchedule(scheduleId);
        return ResponseEntity.ok().body(ApiResponse.of("일정을 단건 조회합니다.", responseDto));
    }

    /**
     * 하나의 일정에 달린 댓글 전체 조회 !! ?? 반환값 ..
     * 응답 dto에 일정id, 댓글 list 쭉 나오게 해야함.
     * 댓글 정보도 다 나와야함
     * 요청값은? 일정 id 를 조회
     * 엔드포인트는 마지막 값이 해당 api임을 보여줘야하기에
     * /{scheduleId}/commentList
     */
//    @GetMapping("/{scheduleId}/commentList")
//    public ResponseEntity<ApiResponse<ScheduleCommentListResponseDto>> findCommentList(
//            @PathVariable Long scheduleId
//    ) {
//        ScheduleCommentListResponseDto responseDto = scheduleService.findCommentList(scheduleId);
//        return ResponseEntity.ok().body(ApiResponse.of("해당 일정의 댓글List가 조회되었습니다.", responseDto));
//    }


    @PutMapping("/{writerId}/{scheduleId}")
    public ResponseEntity<ApiResponse<UpdateScheduleResponseDto>> updateSchedule(
            @NotNull @PathVariable Long writerId,
            @NotNull @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequestDto requestDto
    ) {
        UpdateScheduleResponseDto responseDto = scheduleService.updateSchedule(writerId, scheduleId, requestDto);
        return ResponseEntity.ok().body(ApiResponse.of("일정이 수정되었습니다.", responseDto));
    }

    @DeleteMapping("/{writerId}/{scheduleId}")
    public ResponseEntity<MessageResponse> deleteSchedule(
            @NotNull @PathVariable Long writerId,
            @NotNull @PathVariable Long scheduleId
    ) {
        scheduleService.deleteSchedule(writerId, scheduleId);
        return ResponseEntity.ok().body(MessageResponse.from("일정이 삭제되었습니다."));
    }
}
