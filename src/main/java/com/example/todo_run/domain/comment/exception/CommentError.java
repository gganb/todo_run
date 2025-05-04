package com.example.todo_run.domain.comment.exception;

import com.example.todo_run.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentError implements ErrorCode {

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "C01", "댓글이 존재하지 않습니다."),
    COMMENT_MODIFY_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "C02", "본인이 작성한 댓글만 수정할 수 있습니다."),
    COMMENT_DELETE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "C03", "본인이 작성한 댓글만 삭제할 수 있습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
