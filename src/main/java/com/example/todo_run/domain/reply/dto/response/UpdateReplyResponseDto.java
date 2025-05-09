package com.example.todo_run.domain.reply.dto.response;

import com.example.todo_run.domain.reply.entity.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateReplyResponseDto {

    private final Long writerId;
    private final Long commentId;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private UpdateReplyResponseDto(Long writerId, Long commentId, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.writerId = writerId;
        this.commentId = commentId;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UpdateReplyResponseDto from(Reply reply) {
        return new UpdateReplyResponseDto(
                reply.getWriterId(),
                reply.getParentComment().getId(),
                reply.getContents(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }
}
