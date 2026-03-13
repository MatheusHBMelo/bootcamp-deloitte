package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import dev.matheushbmelo.bootcamp_deloitte.validation.UsuarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioValidator {
    private final List<UsuarioValidation> validators;

    @Autowired
    public UsuarioValidator(List<UsuarioValidation> validators) {
        this.validators = validators;
    }

    public void validaCamposDoUsuario(UsuarioRequestDto usuarioRequestDto) {

        if (usuarioRequestDto == null) {
            throw new UsuarioValidationException("Usuário nulo: digite as informações do usuário!");
        }

        validators.forEach(validation -> validation.validar(usuarioRequestDto));
    }
}
