package com.example.todo_run.domain.reply.dto.response;

import com.example.todo_run.domain.reply.entity.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyDto {
    private final Long id;  // 대댓글 번호 .. 필요한가?
    private final Long writerId;    // 작성자 id
    private final String contents;  // 대댓글 내용
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private ReplyDto(Long id, Long writerId, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.writerId = writerId;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReplyDto from(Reply reply) {
        return new ReplyDto(
                reply.getId(),
                reply.getWriterId(),
                reply.getContents(),
                reply.getCreatedAt(),
                reply.getUpdatedAt());
    }
}
