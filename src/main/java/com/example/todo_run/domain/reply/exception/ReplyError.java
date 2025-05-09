package com.example.todo_run.domain.reply.exception;

import com.example.todo_run.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyError implements ErrorCode {

    REPLY_DUPLICATE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "R01", "하나의 댓글엔 하나의 대댓글만 작성할 수 있습니다."),
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "S02", "대댓글을 찾을 수 없습니다."),
    REPLY_MODIFY_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "S03", "본인이 작성한 댓글만 수정이 가능합니다."),
    REPLY_DELETE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "S04", "본인이 작성한 댓글만 삭제할 수 있습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
