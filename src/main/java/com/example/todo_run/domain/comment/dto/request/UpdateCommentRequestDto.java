package com.example.todo_run.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCommentRequestDto {

    @NotNull(message = "댓글 작성자 id를 입력해주세요.")
    private Long writerId;

    @NotBlank(message = "수정할 내용을 입력해주세요.")
    private String contents;
}
