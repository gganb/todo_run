package com.example.todo_run.domain.inComment.entity;

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

    @OneToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment comment;

    private String contents;

    // 댓글 아래에 대댓글.        ,,,,,,,'
    // 댓글 조회시 대댓글도 같이 보여야함. 삭제시 함께 삭제됨.
    //
    public Reply(Long scheduleId, Long writerId, Comment comment, String contents) {
        this.scheduleId = scheduleId;
        this.writerId = writerId;
        this.comment = comment;
        this.contents = contents;
    }
}
