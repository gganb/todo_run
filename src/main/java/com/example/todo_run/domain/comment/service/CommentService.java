package com.example.todo_run.domain.comment.service;

import com.example.todo_run.domain.comment.dto.request.SaveCommentRequestDto;
import com.example.todo_run.domain.comment.dto.request.UpdateCommentRequestDto;
import com.example.todo_run.domain.comment.dto.response.CommentResponseDto;
import com.example.todo_run.domain.comment.dto.response.SaveCommentResponseDto;
import com.example.todo_run.domain.comment.entity.Comment;
import com.example.todo_run.domain.comment.exception.CommentException;
import com.example.todo_run.domain.comment.repository.CommentRepository;
import com.example.todo_run.domain.schedule.entity.Schedule;
import com.example.todo_run.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.todo_run.domain.comment.exception.CommentError.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleService scheduleService;

    // 댓글 저장
    @Transactional
    public SaveCommentResponseDto saveComment(Long scheduleId, SaveCommentRequestDto requestDto) {
        Schedule findSchedule = scheduleService.findByIdOrElseThrow(scheduleId);

        Comment comment = Comment.toEntity(findSchedule, requestDto);

        comment.setSchedule(findSchedule);

        Comment savedComment = commentRepository.save(comment);
        return SaveCommentResponseDto.from(savedComment);
    }

    // 댓글 리스트 검색
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllComment(Long scheduleId) {
        Schedule findSchedule = scheduleService.findByIdOrElseThrow(scheduleId);

        List<Comment> commentList = commentRepository.findByScheduleIdOrderByUpdatedAtDesc(findSchedule.getId());

        if (commentList.isEmpty()) {
            throw new CommentException(COMMENT_NOT_FOUND);
        }
        return commentList.stream().map(CommentResponseDto::from).toList();
    }

    // 댓글 단건 조회
    @Transactional(readOnly = true)
    public CommentResponseDto findComment(Long commentId) {
        Comment findComment = findCommentOrElseThrow(commentId);

        return CommentResponseDto.from(findComment);
    }

    //  댓글 수정 - 본인의 댓글만 수정 가능
    @Transactional
    public CommentResponseDto updateComment(Long commentId, UpdateCommentRequestDto requestDto) {
        Comment findComment = findCommentOrElseThrow(commentId);

        if (!findComment.getWriterId().equals(requestDto.getWriterId())) {
            throw new CommentException(COMMENT_MODIFY_NOT_ALLOWED);
        }
        findComment.updateComment(requestDto);

        return CommentResponseDto.from(findComment);
    }

    // 댓글 삭제 - 본인의 댓글만 삭제 가능
    @Transactional
    public void deleteComment(Long commentId, Long writerId) {
        Comment findComment = findCommentOrElseThrow(commentId);

        if (!findComment.getWriterId().equals(writerId)) {
            throw new CommentException(COMMENT_DELETE_NOT_ALLOWED);
        }
        commentRepository.delete(findComment);
    }

    private Comment findCommentOrElseThrow(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CommentException(COMMENT_NOT_FOUND)
        );
    }


}
