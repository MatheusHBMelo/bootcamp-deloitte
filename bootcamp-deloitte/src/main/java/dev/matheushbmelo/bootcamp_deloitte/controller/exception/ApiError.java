package dev.matheushbmelo.bootcamp_deloitte.controller.exception;

import java.time.LocalDateTime;

public class ApiError {
    public String message;
    public String error;
    public int status;
    public String path;
    public LocalDateTime timestamp;

    public ApiError() {
    }

    public ApiError(String message, String error, int status, String path, LocalDateTime timestamp) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.path = path;
        this.timestamp = timestamp;
    }
}
