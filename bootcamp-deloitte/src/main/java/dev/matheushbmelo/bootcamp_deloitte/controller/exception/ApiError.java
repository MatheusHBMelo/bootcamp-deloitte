package dev.matheushbmelo.bootcamp_deloitte.controller.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    public String message;
    public String error;
    public int status;
    public String path;
    public LocalDateTime timestamp;
}
