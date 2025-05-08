package com.example.todo_run.domain.inComment.repository;

import com.example.todo_run.domain.inComment.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findInCommentByCommentId(Long commentId);
}
