package com.example.todo_run.domain.schedule.exception;

import com.example.todo_run.common.exception.BaseException;
import com.example.todo_run.common.exception.ErrorCode;

public class ScheduleException extends BaseException {
    public ScheduleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
