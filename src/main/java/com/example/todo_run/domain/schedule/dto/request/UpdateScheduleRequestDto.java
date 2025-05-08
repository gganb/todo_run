package com.example.todo_run.domain.schedule.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateScheduleRequestDto {

    @NotNull(message = "작성자 id를 입력해주세요.")
    private Long scheduleWriterId;

    private String title;
    private String contents;
}
