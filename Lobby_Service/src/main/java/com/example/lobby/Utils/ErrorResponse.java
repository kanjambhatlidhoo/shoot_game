package com.example.lobby.Utils;

import lombok.Data;

@Data
public class ErrorResponse {
    private final int status;
    private final String message;
    private final String cause;
    private final String timestamp;

    public ErrorResponse(int status, String message, String cause) {
        this.status = status;
        this.message = message;
        this.cause = cause;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
}
