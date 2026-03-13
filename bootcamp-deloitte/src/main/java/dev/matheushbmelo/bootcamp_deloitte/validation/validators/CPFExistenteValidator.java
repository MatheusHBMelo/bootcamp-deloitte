package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class CPFExistenteValidator {
    private final UsuarioRepository usuarioRepository;

    public CPFExistenteValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean buscarCpfExistente(String cpf) {
        return this.usuarioRepository.existsByCpf(cpf);
    }
}
