package dev.matheushbmelo.bootcamp_deloitte.mapper;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;

public interface UsuarioMapper {
    Usuario toUsuario(UsuarioRequestDto dto);
    UsuarioResponseDto toResponseDto(Usuario usuario);
    void atualizaCampos(Usuario usuario, UsuarioRequestDto dto);
}
