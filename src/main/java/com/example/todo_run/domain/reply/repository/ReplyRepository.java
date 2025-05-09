package com.example.todo_run.domain.reply.repository;

import com.example.todo_run.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findRepliesByParentComment_Id(Long commentId);
}
