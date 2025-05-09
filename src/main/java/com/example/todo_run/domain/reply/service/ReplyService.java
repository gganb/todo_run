package com.example.todo_run.domain.reply.service;

import com.example.todo_run.domain.comment.entity.Comment;
import com.example.todo_run.domain.comment.service.CommentService;
import com.example.todo_run.domain.reply.dto.request.SaveReplyRequestDto;
import com.example.todo_run.domain.reply.dto.request.UpdateReplyRequestDto;
import com.example.todo_run.domain.reply.dto.response.ReplyResponseDto;
import com.example.todo_run.domain.reply.dto.response.SaveReplyResponseDto;
import com.example.todo_run.domain.reply.dto.response.UpdateReplyResponseDto;
import com.example.todo_run.domain.reply.entity.Reply;
import com.example.todo_run.domain.reply.exception.ReplyException;
import com.example.todo_run.domain.reply.repository.ReplyRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.todo_run.domain.reply.exception.ReplyError.*;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentService commentService;

    @Transactional
    public SaveReplyResponseDto saveReply(Long commentId, SaveReplyRequestDto requestDto) {

        Comment findComment = commentService.findCommentOrElseThrow(commentId);

        Reply reply = Reply.toEntity(requestDto.getWriterId(), findComment, requestDto.getContents());

        replyRepository.save(reply);

        return SaveReplyResponseDto.from(reply);
    }

    // 조회
    @Transactional(readOnly = true)
    public ReplyResponseDto findReplyByComment(Long commentId) {
        Comment parent = commentService.findCommentOrElseThrow(commentId);

        List<Reply> replies = replyRepository.findRepliesByParentComment_Id(commentId);

        return ReplyResponseDto.from(parent,replies);
    }

    // 대댓글 수정
    @Transactional
    public UpdateReplyResponseDto updateReply(Long replyId, @Valid UpdateReplyRequestDto requestDto) {
        Reply findReply = findByIdOrElseThrow(replyId);

        if (!findReply.getWriterId().equals(requestDto.getWriterId())) {
            throw new ReplyException(REPLY_MODIFY_NOT_ALLOWED);
        }

        findReply.updateReply(requestDto.getContents());
        return UpdateReplyResponseDto.from(findReply);
    }

    // 대댓글 삭제
    @Transactional
    public void deleteReply(Long replyId, Long writerId) {
        Reply findReply = findByIdOrElseThrow(replyId);
        if (!findReply.getWriterId().equals(writerId)) {
            throw new ReplyException(REPLY_DELETE_NOT_ALLOWED);
        }
        replyRepository.delete(findReply);
    }

    private Reply findByIdOrElseThrow(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(
                () -> new ReplyException(REPLY_NOT_FOUND));
    }

}
