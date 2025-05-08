package com.example.todo_run.domain.schedule.dto.response;

import com.example.todo_run.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final Long writerId;
    private final String title;
    private final String contents;
    // 댓글 개수
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private ScheduleResponseDto(Long id, Long writerId, String title, String contents, int commentCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ScheduleResponseDto from(Schedule schedule, int commentCount) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContents(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
//