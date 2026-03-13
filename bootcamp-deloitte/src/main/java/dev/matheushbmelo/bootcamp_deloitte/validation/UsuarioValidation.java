package dev.matheushbmelo.bootcamp_deloitte.validation;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;

public interface UsuarioValidation {
    void validar(UsuarioRequestDto usuario);
}
