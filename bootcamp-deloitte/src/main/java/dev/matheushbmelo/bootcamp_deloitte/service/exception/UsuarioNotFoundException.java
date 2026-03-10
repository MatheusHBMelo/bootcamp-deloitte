package dev.matheushbmelo.bootcamp_deloitte.service.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
