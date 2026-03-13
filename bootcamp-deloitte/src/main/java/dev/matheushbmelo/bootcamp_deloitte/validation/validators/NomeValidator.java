package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import dev.matheushbmelo.bootcamp_deloitte.validation.UsuarioValidation;
import org.springframework.stereotype.Component;

@Component
public class NomeValidator implements UsuarioValidation {
    @Override
    public void validar(UsuarioRequestDto usuario) {
        final String NOME_REGEX = "^[A-Za-zÀ-ÿ ]+$";

        if (usuario.nome() == null || usuario.nome().isBlank()) {
            throw new UsuarioValidationException("Nome obrigatório: digite um nome para o usuário!");
        }

        if (usuario.nome().trim().length() < 3) {
            throw new UsuarioValidationException("Nome inválido: deve conter no mínimo 3 letras!");
        }

        if (!usuario.nome().matches(NOME_REGEX)) {
            throw new UsuarioValidationException("Nome inválido: use apenas letras!");
        }
    }
}
