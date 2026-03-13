package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailExistenteValidator {
    private final UsuarioRepository usuarioRepository;

    public EmailExistenteValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean buscarEmailExistente(String email) {
        return this.usuarioRepository.existsByEmail(email);
    }
}
