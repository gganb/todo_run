package com.example.todo_run.domain.schedule.exception;

import com.example.todo_run.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ScheduleError implements ErrorCode {
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "S01", "일정을 찾을 수 없습니다."),
    SCHEDULE_MODIFY_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "S02", "본인의 일정만 수정할 수 있습니다."),
    SCHEDULE_DELETE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "S03", "본인의 일정만 삭제할 수 있습니다."),
    SCHEDULE_UPDATE_NO_FIELDS(HttpStatus.BAD_REQUEST, "S04", "변경할 제목 또는 내용을 하나 이상 입력해주세요."),
    TITLE_IS_EMPTY(HttpStatus.BAD_REQUEST,"S05","제목은 공백이 될 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
