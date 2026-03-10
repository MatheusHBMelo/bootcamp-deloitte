package dev.matheushbmelo.bootcamp_deloitte.controller.dto;

import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;

public record UsuarioResponseDto(Long id, String nome, String email, String cpf, String telefone) {
    public static UsuarioResponseDto fromUsuarioResponseDto(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.id,
                usuario.nome,
                usuario.email,
                usuario.cpf,
                usuario.telefone
        );
    }
}
