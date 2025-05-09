package com.example.todo_run.domain.reply.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveReplyRequestDto {

    @NotNull(message = "작성자 id를 입력해주세요.")
    private Long writerId;

    @NotNull(message = "댓글 내용을 입력해주세요.")
    private String contents;
}
