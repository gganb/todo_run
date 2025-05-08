package com.example.todo_run.domain.comment.service;

import com.example.todo_run.domain.comment.dto.request.SaveCommentRequestDto;
import com.example.todo_run.domain.comment.dto.request.UpdateCommentRequestDto;
import com.example.todo_run.domain.comment.dto.response.CommentResponseDto;
import com.example.todo_run.domain.comment.dto.response.SaveCommentResponseDto;
import com.example.todo_run.domain.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    SaveCommentResponseDto saveComment(Long scheduleId, SaveCommentRequestDto requestDto);

    List<CommentResponseDto> findAllComment(Long scheduleId);

    CommentResponseDto findComment(Long commentId);

    CommentResponseDto updateComment(Long commentId, UpdateCommentRequestDto requestDto);

    void deleteComment(Long commentId, Long writerId);

    int countByScheduleId(Long scheduleId);

    List<CommentResponseDto> findAllCommentOrderByCreatedAtAsc(Long scheduleId);

    Comment findCommentOrElseThrow(Long commentId);
}
