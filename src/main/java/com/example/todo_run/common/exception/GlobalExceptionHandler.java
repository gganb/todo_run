package com.example.todo_run.common.exception;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ExceptionResponse> handleCustomException(BaseException e) {
        return ExceptionResponse.dtoResponseEntity(e.getErrorCode());
    }

    //TODO : valid 예외처리 안됨
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleValidException(MethodArgumentNotValidException e) {
        log.info("valid");
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
