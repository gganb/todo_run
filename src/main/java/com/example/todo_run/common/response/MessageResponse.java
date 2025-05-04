package com.example.todo_run.common.response;

import lombok.Getter;

@Getter
public class MessageResponse {
    private final String message;

    private MessageResponse(String message) {
        this.message = message;
    }

    public static MessageResponse from(String message) {
        return new MessageResponse(message);
    }
}
