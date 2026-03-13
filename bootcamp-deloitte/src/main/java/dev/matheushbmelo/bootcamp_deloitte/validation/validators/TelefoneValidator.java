package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import dev.matheushbmelo.bootcamp_deloitte.validation.UsuarioValidation;
import org.springframework.stereotype.Component;

@Component
public class TelefoneValidator implements UsuarioValidation {
    private final UsuarioRepository usuarioRepository;

    public TelefoneValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void validar(UsuarioRequestDto usuario) {
        if (usuario.telefone() == null || usuario.telefone().isBlank()) {
            throw new UsuarioValidationException("Telefone obrigatorio: informe o telefone do usuario");
        }

        if (usuario.telefone().length() < 9 || usuario.telefone().length() > 11) {
            throw new UsuarioValidationException("Telefone invalido: deve conter 9 ou 11 dígitos.");
        }

        if (!usuario.telefone().matches("\\d+")) {
            throw new UsuarioValidationException("Telefone invalido: apenas números são permitidos.");
        }

        if (this.usuarioRepository.existsByTelefone(usuario.telefone())) {
            throw new UsuarioValidationException("Operação não permitida: já existe um usuário cadastrado com esse telefone!");
        }
    }
}
