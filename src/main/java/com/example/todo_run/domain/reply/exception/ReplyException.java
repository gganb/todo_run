package com.example.todo_run.domain.reply.exception;

import com.example.todo_run.common.exception.BaseException;
import com.example.todo_run.common.exception.ErrorCode;

public class ReplyException extends BaseException {

    public ReplyException(ErrorCode errorCode) {
        super(errorCode);
    }
}
