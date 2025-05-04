package com.example.todo_run.domain.comment.controller;

import com.example.todo_run.common.response.ApiResponse;
import com.example.todo_run.common.response.MessageResponse;
import com.example.todo_run.domain.comment.dto.request.SaveCommentRequestDto;
import com.example.todo_run.domain.comment.dto.request.UpdateCommentRequestDto;
import com.example.todo_run.domain.comment.dto.response.CommentResponseDto;
import com.example.todo_run.domain.comment.dto.response.SaveCommentResponseDto;
import com.example.todo_run.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    // 댓글 저장
    @PostMapping("/{scheduleId}/comments")
    public ResponseEntity<ApiResponse<SaveCommentResponseDto>> saveComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody SaveCommentRequestDto requestDto
    ) {
        SaveCommentResponseDto responseDto = commentService.saveComment(scheduleId, requestDto);
        return ResponseEntity.status(201).body(ApiResponse.of("댓글이 저장되었습니다.", responseDto));
    }

    // 일정에 달린 모든 댓글 조회
    @GetMapping("/comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> findAllComment(
            @RequestParam Long scheduleId
    ) {
        List<CommentResponseDto> commentList = commentService.findAllComment(scheduleId);
        return ResponseEntity.ok().body(ApiResponse.of(scheduleId + "번 일정의 모든 댓글을 조회합니다.", commentList));
    }

    // 일정 단건 조회
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> findComment(
            @PathVariable Long commentId
    ) {
        log.info("댓글 단건 조회");
        CommentResponseDto responseDto = commentService.findComment(commentId);
        return ResponseEntity.ok().body(ApiResponse.of("해당 댓글이 조회되었습니다.", responseDto));

    }


    // 댓글 수정 - 댓글 작성자만 수정 가능
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequestDto requestDto
    ) {
        CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok().body(ApiResponse.of("댓글이 수정되었습니다.", responseDto));
    }

    // 댓글 삭제 - 댓글 작성자만 삭제 가능
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long writerId
    ) {
        commentService.deleteComment(commentId, writerId);
        return ResponseEntity.ok().body(MessageResponse.from("댓글이 삭제되었습니다."));
    }
}
