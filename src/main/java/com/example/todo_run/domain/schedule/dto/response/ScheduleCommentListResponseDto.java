package com.example.todo_run.domain.schedule.dto.response;

import com.example.todo_run.domain.comment.dto.response.CommentResponseDto;
import com.example.todo_run.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleCommentListResponseDto {
    // 일정 Id
    private final Long scheduleId;

    private final Long scheduleWriterId;

    private final String title;

    private final String contents;
    // 댓글 리스트
    private final List<CommentResponseDto> commentList;

    private ScheduleCommentListResponseDto(
            Long scheduleId,
            Long scheduleWriterId,
            String title,
            String contents,
            List<CommentResponseDto> commentList) {

        this.scheduleId = scheduleId;
        this.scheduleWriterId = scheduleWriterId;
        this.title = title;
        this.contents = contents;
        this.commentList = commentList;
    }

    public static ScheduleCommentListResponseDto from(Schedule schedule, List<CommentResponseDto> commentList) {
        return new ScheduleCommentListResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContents(),
                commentList);

    }
}
