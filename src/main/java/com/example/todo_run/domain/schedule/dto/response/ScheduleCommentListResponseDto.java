package com.example.todo_run.domain.schedule.dto.response;

import com.example.todo_run.domain.comment.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleCommentListResponseDto {
    // 일정 Id
    private Long scheduleId;
    // 댓글 리스트 ........................
    private List<Comment> commentList;
}
