package com.example.todo_run.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveCommentRequestDto {

    @NotNull(message = "작성자 id를 입력해주세요.")
    private Long writerId;

    @NotBlank(message = "댓글을 입력해주세요.")
    private String contents;
}
