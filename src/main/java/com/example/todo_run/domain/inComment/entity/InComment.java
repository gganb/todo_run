//package com.example.todo_run.domain.inComment.entity;
//
//import com.example.todo_run.common.entity.BaseEntity;
//import com.example.todo_run.domain.comment.entity.Comment;
//import com.example.todo_run.domain.schedule.entity.Schedule;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//
//
//@Entity
//@Table(name = "in_comments")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class InComment extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;
//
//    private Long writerId;
//
//    @JoinColumn(name = "parent_comment_id")
//    private Comment comment;
//
//    private String contents;
//
//}
