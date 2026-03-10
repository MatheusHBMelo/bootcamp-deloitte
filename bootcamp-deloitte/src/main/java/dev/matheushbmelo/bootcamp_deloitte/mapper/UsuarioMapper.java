package dev.matheushbmelo.bootcamp_deloitte.mapper;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.nome = usuarioRequestDto.nome();
        usuario.email = usuarioRequestDto.email();
        usuario.cpf = usuarioRequestDto.cpf();
        usuario.telefone = usuarioRequestDto.telefone();

        return usuario;
    }
}
