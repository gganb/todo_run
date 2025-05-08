package com.example.todo_run.domain.inComment.controller;

import com.example.todo_run.common.response.ApiResponse;
import com.example.todo_run.domain.inComment.dto.request.SaveReplyRequestDto;
import com.example.todo_run.domain.inComment.dto.response.SaveReplyResponseDto;
import com.example.todo_run.domain.inComment.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/{commentId}/replies")
    public ResponseEntity<ApiResponse<SaveReplyResponseDto>> saveIncomment(
            @PathVariable Long commentId,
            @RequestBody SaveReplyRequestDto requestDto
    ) {
        SaveReplyResponseDto responseDto = replyService.saveIncomment(commentId, requestDto);
        return ResponseEntity.status(201).body(ApiResponse.of("대댓글이 작성되었습니다.", responseDto));
    }


}
