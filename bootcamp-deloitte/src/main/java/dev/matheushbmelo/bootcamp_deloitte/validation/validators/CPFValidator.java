package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import dev.matheushbmelo.bootcamp_deloitte.validation.UsuarioValidation;
import org.springframework.stereotype.Component;

@Component
public class CPFValidator implements UsuarioValidation {
    private final CPFExistenteValidator cpfExistenteValidator;

    public CPFValidator(CPFExistenteValidator cpfExistenteValidator) {
        this.cpfExistenteValidator = cpfExistenteValidator;
    }

    @Override
    public void validar(UsuarioRequestDto usuario) {
        if (usuario.cpf() == null || usuario.cpf().isBlank()) {
            throw new UsuarioValidationException("CPF obrigatório: digite o cpf do usuario!");
        }

        String cpf = usuario.cpf().replaceAll("\\D", "");

        if (cpf.trim().length() != 11) {
            throw new UsuarioValidationException("CPF invalido: deve conter 11 dígitos!");
        }

        if (!cpf.matches("\\d{11}")) {
            throw new UsuarioValidationException("CPF inválido: apenas números são permitidos!");
        }

        if (this.cpfExistenteValidator.buscarCpfExistente(usuario.cpf())) {
            throw new UsuarioValidationException("Operação não permitida: já existe um usuário cadastrado com esse cpf!");
        }
    }
}
