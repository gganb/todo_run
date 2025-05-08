package com.example.todo_run.domain.inComment.service;

import com.example.todo_run.domain.comment.entity.Comment;
import com.example.todo_run.domain.comment.service.CommentService;
import com.example.todo_run.domain.inComment.dto.request.SaveReplyRequestDto;
import com.example.todo_run.domain.inComment.dto.response.SaveReplyResponseDto;
import com.example.todo_run.domain.inComment.entity.Reply;
import com.example.todo_run.domain.inComment.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentService commentService;

    @Transactional
    public SaveReplyResponseDto saveIncomment(Long commentId, SaveReplyRequestDto requestDto) {
        // 저장할 때 어떻게 저장해야할까.....

        if (replyRepository.findInCommentByCommentId(commentId).isPresent()) {
            throw new IllegalArgumentException("하나의 댓글엔 하나의 대댓글만 작성 가능합니다.");
        }
        Comment findComment = commentService.findCommentOrElseThrow(commentId);

        Reply reply = new Reply(findComment.getSchedule().getId(), requestDto.getWriterId(), findComment, requestDto.getContents());
        replyRepository.save(reply);

        return SaveReplyResponseDto.from(reply);

    }
}
