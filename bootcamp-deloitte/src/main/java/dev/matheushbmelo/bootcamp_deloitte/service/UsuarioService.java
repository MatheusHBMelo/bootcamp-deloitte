package dev.matheushbmelo.bootcamp_deloitte.service;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import dev.matheushbmelo.bootcamp_deloitte.mapper.UsuarioMapper;
import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDto adicionarUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario novoUsuario = UsuarioMapper.toUsuario(usuarioRequestDto);
        return UsuarioResponseDto.fromUsuarioResponseDto(this.usuarioRepository.save(novoUsuario));
    }

    public UsuarioResponseDto listarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario de ID " + id + " não encontrado"));

        return UsuarioResponseDto.fromUsuarioResponseDto(usuario);
    }

    public List<UsuarioResponseDto> listarTodosUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioResponseDto::fromUsuarioResponseDto).toList();
    }

    public void removerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário de ID " + id + " não encontrado"));
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDto editarUsuario(Long id, UsuarioRequestDto usuarioEditado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario de ID " + id + " não encontrado"));

        usuario.nome = usuarioEditado.nome();
        usuario.email = usuarioEditado.email();
        usuario.cpf = usuarioEditado.cpf();
        usuario.telefone = usuarioEditado.telefone();

        return UsuarioResponseDto.fromUsuarioResponseDto(this.usuarioRepository.save(usuario));
    }
}
