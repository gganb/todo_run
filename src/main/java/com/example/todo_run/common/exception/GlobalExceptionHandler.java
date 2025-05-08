package com.example.todo_run.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ExceptionResponse> handleCustomException(BaseException e) {
        return ExceptionResponse.dtoResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleValidException(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String code = "VALIDATION_FAILED";
        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity.status(status.value())
                .body(ExceptionResponse.builder()
                        .status(status.value())
                        .code(code)
                        .message(message)
                        .build());
    }

}
