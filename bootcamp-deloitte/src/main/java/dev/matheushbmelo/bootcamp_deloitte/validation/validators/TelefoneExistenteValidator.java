package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class TelefoneExistenteValidator {
    private final UsuarioRepository usuarioRepository;

    public TelefoneExistenteValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean buscarTelefoneExistente(String telefone) {
        return this.usuarioRepository.existsByTelefone(telefone);
    }
}
