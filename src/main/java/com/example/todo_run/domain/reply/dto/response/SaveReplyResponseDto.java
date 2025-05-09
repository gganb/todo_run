package com.example.todo_run.domain.reply.dto.response;

import com.example.todo_run.domain.reply.entity.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveReplyResponseDto {

    private final Long writerId;
    private final Long commentId;
    private final String contents;
    private final LocalDateTime createdAt;


    private SaveReplyResponseDto(Long writerId, Long commentId, String contents, LocalDateTime createdAt) {
        this.writerId = writerId;
        this.commentId = commentId;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public static SaveReplyResponseDto from(Reply reply) {
        return new SaveReplyResponseDto(
                reply.getWriterId(),
                reply.getParentComment().getId(),
                reply.getContents(),
                reply.getCreatedAt()
        );
    }
}
