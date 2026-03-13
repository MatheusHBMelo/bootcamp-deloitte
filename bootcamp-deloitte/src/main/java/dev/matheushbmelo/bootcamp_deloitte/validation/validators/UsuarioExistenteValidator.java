package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsuarioExistenteValidator {
    private final UsuarioRepository usuarioRepository;

    public UsuarioExistenteValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarUsuarioExistentePorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario de ID " + id + " não encontrado"));
    }
}
