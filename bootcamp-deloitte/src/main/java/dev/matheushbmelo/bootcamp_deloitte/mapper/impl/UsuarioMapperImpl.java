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
        usuario.cpf = usuarioRequestDto.cpf();
        usuario.telefone = usuarioRequestDto.telefone();

        return usuario;
    }

    @Override
    public UsuarioResponseDto toResponseDto(Usuario usuario) {
        return UsuarioResponseDto.fromUsuarioResponseDto(usuario);
    }

    @Override
    public void atualizaCampos(Usuario usuario, UsuarioRequestDto usuarioEditado) {
        usuario.nome = usuarioEditado.nome();
        usuario.email = usuarioEditado.email();
        usuario.cpf = usuarioEditado.cpf();
        usuario.telefone = usuarioEditado.telefone();
    }
}
