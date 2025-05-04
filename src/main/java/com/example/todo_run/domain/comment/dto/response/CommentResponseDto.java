package com.example.todo_run.domain.comment.dto.response;

import com.example.todo_run.domain.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final Long writerId;
    private final Long scheduleId;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private CommentResponseDto(Long id,
                              Long writerId,
                              Long scheduleId,
                              String contents,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
        this.id = id;
        this.writerId = writerId;
        this.scheduleId = scheduleId;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getWriterId(),
                comment.getSchedule().getId(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
