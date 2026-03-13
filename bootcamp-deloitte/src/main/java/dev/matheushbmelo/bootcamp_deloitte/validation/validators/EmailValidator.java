package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import dev.matheushbmelo.bootcamp_deloitte.validation.UsuarioValidation;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements UsuarioValidation {
    private final EmailExistenteValidator emailExistenteValidator;

    public EmailValidator(EmailExistenteValidator emailExistenteValidator) {
        this.emailExistenteValidator = emailExistenteValidator;
    }

    @Override
    public void validar(UsuarioRequestDto usuario) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (this.emailExistenteValidator.buscarEmailExistente(usuario.email())){
            throw new UsuarioValidationException("Operação não permitida: já existe um usuário cadastrado com esse email!");
        }

        if (usuario.email() == null || !usuario.email().matches(EMAIL_REGEX)) {
            throw new UsuarioValidationException("Email inválido: Informe um email válido!");
        }
    }
}
