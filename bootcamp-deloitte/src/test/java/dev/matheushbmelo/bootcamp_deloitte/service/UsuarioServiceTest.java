package dev.matheushbmelo.bootcamp_deloitte.service;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    UsuarioRequestDto request;
    Usuario usuarioSalvo;
    UsuarioRequestDto usuarioEditado;
    Long id = 1L;

    @BeforeEach
    void setup() {
        id = 1L;
        request = new UsuarioRequestDto("Matheus", "matheus@email.com", "123.456.789-01", "81999999999");
        usuarioSalvo = new Usuario(1L, "Matheus", "matheus@email.com", "123.456.789-01", "81999999999");
        usuarioEditado = new UsuarioRequestDto("Matheus Barbosa", "matheusbarbosa@email.com", "123.456.789-02", "81000000000");
    }

    @Test
    @DisplayName("Cadastra usuário com sucesso")
    void deveCadastrarUsuarioComSucesso() {
        when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuarioSalvo);

        UsuarioResponseDto resultado = usuarioService.adicionarUsuario(request);

        assertNotNull(resultado);
        assertEquals("Matheus", resultado.nome());
        assertEquals("matheus@email.com", resultado.email());
        assertEquals("123.456.789-01", resultado.cpf());
        assertEquals("81999999999", resultado.telefone());

        verify(usuarioRepository).save(any(Usuario.class));
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Retorna usuário com sucesso")
    void deveRetornarUsuarioComSucesso() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioSalvo));

        UsuarioResponseDto resultado = usuarioService.listarUsuario(id);

        assertNotNull(resultado);
        assertEquals("Matheus", resultado.nome());
        assertEquals("matheus@email.com", resultado.email());
        assertEquals("123.456.789-01", resultado.cpf());
        assertEquals("81999999999", resultado.telefone());

        verify(usuarioRepository).findById(anyLong());
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Retorna exceção a buscar usuário inexistente")
    void deveRetornarExcecaoAoBuscarUsuarioInexistente() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        UsuarioNotFoundException resultado = assertThrows(UsuarioNotFoundException.class,
                () -> usuarioService.listarUsuario(id)
        );

        assertEquals(UsuarioNotFoundException.class, resultado.getClass());
        assertEquals("Usuario de ID " + id + " não encontrado", resultado.getMessage());

        verify(usuarioRepository).findById(anyLong());
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Retorna lista de todos usuarios")
    void deveRetornarTodosUsuariosComSucesso() {
        List<Usuario> usuarios = List.of(
                usuarioSalvo,
                new Usuario(2L, "Renato", "renato@email.com", "987.654.321-01", "81888888888")
        );

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioResponseDto> resultado = usuarioService.listarTodosUsuarios();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Matheus", resultado.get(0).nome());
        assertEquals("Renato", resultado.get(1).nome());

        verify(usuarioRepository).findAll();
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Retorna lista vazia de usuarios")
    void deveRetornarListaVaziaQuandoNaoTiverUsuarioCadastrado() {
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        List<UsuarioResponseDto> resultado = usuarioService.listarTodosUsuarios();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(usuarioRepository).findAll();
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Deleta usuario com sucesso")
    void deveDeletarUsuarioComSucesso() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioSalvo));

        usuarioService.removerUsuario(id);

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).delete(usuarioSalvo);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Retorna excecao ao tentar deletar usuário que não existe")
    void deveRetornarExcecaoAoDeletarUsuarioQueNaoExiste() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        UsuarioNotFoundException resultado = assertThrows(UsuarioNotFoundException.class,
                () -> usuarioService.removerUsuario(id)
        );

        assertEquals(UsuarioNotFoundException.class, resultado.getClass());
        assertEquals("Usuário de ID " + id + " não encontrado", resultado.getMessage());

        verify(usuarioRepository).findById(anyLong());
        verify(usuarioRepository, never()).delete(any(Usuario.class));
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Retorna usuário editado com sucesso")
    void deveEditarUsuarioComSucesso() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioSalvo));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        UsuarioResponseDto resultado = usuarioService.editarUsuario(id, usuarioEditado);

        assertNotNull(resultado);
        assertEquals("Matheus Barbosa", resultado.nome());
        assertEquals("matheusbarbosa@email.com", resultado.email());
        assertEquals("123.456.789-02", resultado.cpf());
        assertEquals("81000000000", resultado.telefone());

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).save(usuarioSalvo);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Retorna exceção quando tenta editar usuário que não existe")
    void deveRetornarExcecaoAoEditarUsuarioQueNaoExiste() {
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        UsuarioNotFoundException exception = assertThrows(UsuarioNotFoundException.class,
                () -> usuarioService.editarUsuario(id, usuarioEditado)
        );

        assertEquals("Usuario de ID " + id + " não encontrado", exception.getMessage());

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository, never()).save(any());
        verifyNoMoreInteractions(usuarioRepository);
    }
}