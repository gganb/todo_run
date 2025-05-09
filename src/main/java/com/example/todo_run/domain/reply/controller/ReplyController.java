package com.example.todo_run.domain.reply.controller;

import com.example.todo_run.common.response.ApiResponse;
import com.example.todo_run.common.response.MessageResponse;
import com.example.todo_run.domain.reply.dto.request.SaveReplyRequestDto;
import com.example.todo_run.domain.reply.dto.request.UpdateReplyRequestDto;
import com.example.todo_run.domain.reply.dto.response.ReplyResponseDto;
import com.example.todo_run.domain.reply.dto.response.SaveReplyResponseDto;
import com.example.todo_run.domain.reply.dto.response.UpdateReplyResponseDto;
import com.example.todo_run.domain.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    /**
     * 대댓글 작성 API
     */
    @PostMapping("/{commentId}/replies")
    public ResponseEntity<ApiResponse<SaveReplyResponseDto>> saveReply(
            @PathVariable Long commentId,
            @RequestBody SaveReplyRequestDto requestDto
    ) {
        SaveReplyResponseDto responseDto = replyService.saveReply(commentId, requestDto);
        return ResponseEntity.status(201).body(ApiResponse.of("대댓글이 작성되었습니다.", responseDto));
    }

    /**
     * 대댓글 조회 API
     */
    @GetMapping("/{commentId}/replies")
    public ResponseEntity<ApiResponse<ReplyResponseDto>> findReply(
            @PathVariable Long commentId
    ) {
        ReplyResponseDto responseDto = replyService.findReplyByComment(commentId);
        return ResponseEntity.ok().body(ApiResponse.of("댓글을 조회합니다.", responseDto));
    }


    /**
     * 대댓글 수정 API
     */
    @PutMapping("/replies/{replyId}")
    public ResponseEntity<ApiResponse<UpdateReplyResponseDto>> updateReply(
            @PathVariable Long replyId,
            @Valid @RequestBody UpdateReplyRequestDto requestDto
    ) {
        UpdateReplyResponseDto responseDto = replyService.updateReply(replyId, requestDto);
        return ResponseEntity.ok().body(ApiResponse.of("댓글이 수정되었습니다.", responseDto));
    }


    /**
     * 대댓글 삭제 API
     */
    @DeleteMapping("/replies/{replyId}")
    public ResponseEntity<MessageResponse> deleteReply(
            @PathVariable Long replyId,
            @RequestParam Long writerId
    ) {
        replyService.deleteReply(replyId, writerId);
        return ResponseEntity.ok().body(MessageResponse.from("댓글이 삭제되었습니다."));
    }
}
