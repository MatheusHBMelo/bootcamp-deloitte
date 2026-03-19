package dev.matheushbmelo.bootcamp_deloitte.controller.exception;

import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioNotFoundException;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ApiError> usuarioNotFound(UsuarioNotFoundException ex, WebRequest request) {
        ApiError error = new ApiError(
                "Not Found Exception",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsuarioValidationException.class)
    public ResponseEntity<ApiError> usuarioValidationFailed(UsuarioValidationException ex, WebRequest request) {
        ApiError error = new ApiError(
                "Validation Field Exception",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> dataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        ApiError error = new ApiError(
                "Validation Field Exception",
                "Operação não permitida: já existe um usuário cadastrado com esses dados!",
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
