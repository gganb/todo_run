package com.example.todo_run.domain.comment.repository;

import com.example.todo_run.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByScheduleIdOrderByUpdatedAtDesc(Long id);
}
