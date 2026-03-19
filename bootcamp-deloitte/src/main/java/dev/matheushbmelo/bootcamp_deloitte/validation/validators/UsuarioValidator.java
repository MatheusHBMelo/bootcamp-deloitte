package dev.matheushbmelo.bootcamp_deloitte.validation.validators;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import dev.matheushbmelo.bootcamp_deloitte.validation.UsuarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioValidator {
    private final List<UsuarioValidation> validators;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioValidator(List<UsuarioValidation> validators, UsuarioRepository usuarioRepository) {
        this.validators = validators;
        this.usuarioRepository = usuarioRepository;
    }

    public void validaCamposDoUsuario(UsuarioRequestDto usuarioRequestDto) {

        if (usuarioRequestDto == null) {
            throw new UsuarioValidationException("Usuário nulo: digite as informações do usuário!");
        }

        validators.forEach(validation -> validation.validar(usuarioRequestDto));
    }

    public void validarDadosEditados(UsuarioRequestDto usuarioEditado, Long idAtual) {
        if (usuarioEditado.email() != null) {
            usuarioRepository.findByEmail(usuarioEditado.email()).ifPresent(usuario -> {
                if (!usuario.id.equals(idAtual)) {
                    throw new UsuarioValidationException("Erro ao editar: O e-mail digitado já está em uso.");
                }
            });
        }

        if (usuarioEditado.cpf() != null) {
            usuarioRepository.findByCpf(usuarioEditado.cpf()).ifPresent(usuario -> {
                if (!usuario.id.equals(idAtual)) {
                    throw new UsuarioValidationException("Erro ao editar: O CPF já está em uso.");
                }
            });
        }

        if (usuarioEditado.telefone() != null) {
            usuarioRepository.findByTelefone(usuarioEditado.telefone()).ifPresent(usuario -> {
                if (!usuario.id.equals(idAtual)) {
                    throw new UsuarioValidationException("Erro ao editar: O telefone já está em uso.");
                }
            });
        }
    }
}
