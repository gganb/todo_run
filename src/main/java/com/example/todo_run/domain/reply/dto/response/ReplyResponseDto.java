package com.example.todo_run.domain.reply.dto.response;

import com.example.todo_run.domain.comment.entity.Comment;
import com.example.todo_run.domain.reply.entity.Reply;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonPropertyOrder({"commentId", "writerId", "contents", "createdAt", "updatedAt", "replies"})
public class ReplyResponseDto {

    // 부모댓글
    private final Long commentId;
    private final Long writerId;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private final List<ReplyDto> replies;

    private ReplyResponseDto(Comment comment, List<ReplyDto> replies) {
        this.commentId = comment.getId();
        this.writerId = comment.getWriterId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.replies = replies;
    }


    public static ReplyResponseDto from(Comment comment, List<Reply> replies) {
        List<ReplyDto> replyDto = replies.stream().map(ReplyDto::from).toList();

        return new ReplyResponseDto(comment, replyDto);
    }
}
