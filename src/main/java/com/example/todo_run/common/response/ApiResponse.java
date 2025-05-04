package com.example.todo_run.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final String message;
    private final T data;


    private ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(String message, T data) {
        return new ApiResponse<>(message, data);
    }
}
