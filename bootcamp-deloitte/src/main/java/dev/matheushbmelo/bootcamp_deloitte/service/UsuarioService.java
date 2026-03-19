package dev.matheushbmelo.bootcamp_deloitte.service;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import dev.matheushbmelo.bootcamp_deloitte.mapper.impl.UsuarioMapperImpl;
import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import dev.matheushbmelo.bootcamp_deloitte.validation.validators.UsuarioExistenteValidator;
import dev.matheushbmelo.bootcamp_deloitte.validation.validators.UsuarioValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidator usuarioValidator;
    private final UsuarioExistenteValidator usuarioExistenteValidator;
    private final UsuarioMapperImpl usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioValidator usuarioValidator, UsuarioExistenteValidator usuarioExistenteValidator, UsuarioMapperImpl usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioValidator = usuarioValidator;
        this.usuarioExistenteValidator = usuarioExistenteValidator;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional
    public UsuarioResponseDto adicionarUsuario(UsuarioRequestDto usuarioRequestDto) {
        this.usuarioValidator.validaCamposDoUsuario(usuarioRequestDto);

        Usuario novoUsuario = this.usuarioMapper.toUsuario(usuarioRequestDto);

        return this.usuarioMapper.toResponseDto(this.usuarioRepository.save(novoUsuario));
    }

    public UsuarioResponseDto listarUsuario(Long id) {
        Usuario usuarioEncontrado = this.usuarioExistenteValidator.buscarUsuarioExistentePorId(id);

        return this.usuarioMapper.toResponseDto(usuarioEncontrado);
    }

    public List<UsuarioResponseDto> listarTodosUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(usuarioMapper::toResponseDto)
                .toList();
    }

    @Transactional
    public void removerUsuario(Long id) {
        Usuario usuarioEncontrado = this.usuarioExistenteValidator.buscarUsuarioExistentePorId(id);

        usuarioRepository.delete(usuarioEncontrado);
    }

    @Transactional
    public UsuarioResponseDto editarUsuario(Long id, UsuarioRequestDto usuarioEditado) {
        Usuario usuarioAtual = this.usuarioExistenteValidator.buscarUsuarioExistentePorId(id);

        this.usuarioValidator.validarDadosEditados(usuarioEditado, id);

        this.usuarioMapper.atualizaCampos(usuarioAtual, usuarioEditado);

        return this.usuarioMapper.toResponseDto(this.usuarioRepository.save(usuarioAtual));
    }
}
