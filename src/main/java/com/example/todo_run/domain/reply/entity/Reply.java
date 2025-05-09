package com.example.todo_run.domain.reply.entity;

import com.example.todo_run.common.entity.BaseEntity;
import com.example.todo_run.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "replies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scheduleId;

    private Long writerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    private String contents;

    private Reply(Long scheduleId, Long writerId, Comment parentComment, String contents) {
        this.scheduleId = scheduleId;
        this.writerId = writerId;
        this.parentComment = parentComment;
        this.contents = contents;
    }

    public static Reply toEntity(Long writerId, Comment parentComment, String contents) {
        return new Reply(parentComment.getSchedule().getId(), writerId, parentComment, contents);
    }

    public void updateReply(String contents) {
        this.contents = contents;
    }
}
