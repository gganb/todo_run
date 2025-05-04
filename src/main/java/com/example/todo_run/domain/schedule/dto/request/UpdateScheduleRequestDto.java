package com.example.todo_run.domain.schedule.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateScheduleRequestDto {

    private String title;
    private String contents;
}
