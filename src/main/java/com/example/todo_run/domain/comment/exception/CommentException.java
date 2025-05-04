package com.example.todo_run.domain.comment.exception;

import com.example.todo_run.common.exception.BaseException;
import com.example.todo_run.common.exception.ErrorCode;

public class CommentException extends BaseException {
    public CommentException(ErrorCode errorCode){
        super(errorCode);
    }
}
