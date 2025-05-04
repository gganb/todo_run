package com.example.todo_run.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ExceptionResponse {

    private int status;
    private String code;
    private String message;

    public static ResponseEntity<ExceptionResponse> dtoResponseEntity(ErrorCode e) {
        return ResponseEntity.status(e.getStatus().value())
                .body(ExceptionResponse.builder()
                        .status(e.getStatus().value())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }
}
