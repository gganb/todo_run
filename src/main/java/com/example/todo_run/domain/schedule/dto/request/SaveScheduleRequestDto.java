package com.example.todo_run.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveScheduleRequestDto {
    @NotNull(message = "작성자 id를 입력해주세요.")
    private Long writerId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "일정 내용을 입력해주세요.")
    private String contents;

}
