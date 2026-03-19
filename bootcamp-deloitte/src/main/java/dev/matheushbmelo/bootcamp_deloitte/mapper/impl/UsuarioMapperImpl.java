package dev.matheushbmelo.bootcamp_deloitte.mapper.impl;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import dev.matheushbmelo.bootcamp_deloitte.mapper.UsuarioMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {
    @Override
    public Usuario toUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.nome = usuarioRequestDto.nome();
        usuario.email = usuarioRequestDto.email();
        usuario.cpf = usuarioRequestDto.cpf().replaceAll("\\D", "");
        usuario.telefone = usuarioRequestDto.telefone().replaceAll("\\D", "");

        return usuario;
    }

    @Override
    public UsuarioResponseDto toResponseDto(Usuario usuario) {
        return UsuarioResponseDto.fromUsuarioResponseDto(usuario);
    }

    @Override
    public void atualizaCampos(Usuario usuario, UsuarioRequestDto usuarioEditado) {
        if (usuarioEditado.nome() != null && !usuarioEditado.nome().isBlank()) {
            usuario.nome = usuarioEditado.nome();
        }

        if (usuarioEditado.email() != null && !usuarioEditado.email().isBlank()) {
            usuario.email = usuarioEditado.email();
        }

        if (usuarioEditado.cpf() != null && !usuarioEditado.cpf().isBlank()) {
            usuario.cpf = usuarioEditado.cpf().replaceAll("\\D", "");
        }

        if (usuarioEditado.telefone() != null && !usuarioEditado.telefone().isBlank()) {
            usuario.telefone = usuarioEditado.telefone().replaceAll("\\D", "");
        }
    }
}
