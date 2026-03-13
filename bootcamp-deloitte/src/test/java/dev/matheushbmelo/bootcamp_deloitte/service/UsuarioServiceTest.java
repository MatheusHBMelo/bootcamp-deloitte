package dev.matheushbmelo.bootcamp_deloitte.service;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import dev.matheushbmelo.bootcamp_deloitte.mapper.impl.UsuarioMapperImpl;
import dev.matheushbmelo.bootcamp_deloitte.repository.UsuarioRepository;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioValidationException;
import dev.matheushbmelo.bootcamp_deloitte.validation.validators.UsuarioValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Essa classe é a resposta do DESAFIO EXTRA (opcional): Testar o fluxo completo criando um novo usuário
// Esses testes são especificos para o commit do SOLID e nada tem a ver com os testes da branch TEST pois a branch TEST não tem a atualização do código com SOLID
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapperImpl usuarioMapper;

    @Mock
    private UsuarioValidator usuarioValidator;

    UsuarioRequestDto request;
    Usuario usuarioSalvo;
    UsuarioResponseDto responseEsperado;
    Long id = 1L;

    @BeforeEach
    void setup() {
        id = 1L;
        request = new UsuarioRequestDto("Matheus", "matheus@email.com", "123.456.789-01", "81999999999");
        usuarioSalvo = new Usuario(1L, "Matheus", "matheus@email.com", "123.456.789-01", "81999999999");
        responseEsperado = new UsuarioResponseDto(1L, "Matheus", "matheus@email.com", "123.456.789-01", "81999999999");
    }

    @Test
    @DisplayName("Cadastra novo usuário com sucesso")
    void deveCadastrarUmNovoUsuarioComSucesso() {
        when(usuarioMapper.toUsuario(request)).thenReturn(usuarioSalvo);
        when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuarioSalvo);
        when(usuarioMapper.toResponseDto(usuarioSalvo)).thenReturn(responseEsperado);

        UsuarioResponseDto resultado = usuarioService.adicionarUsuario(request);

        assertNotNull(resultado);
        assertEquals("Matheus", resultado.nome());
        assertEquals("matheus@email.com", resultado.email());
        assertEquals("123.456.789-01", resultado.cpf());
        assertEquals("81999999999", resultado.telefone());

        verify(usuarioValidator).validaCamposDoUsuario(request);
        verify(usuarioMapper).toUsuario(request);
        verify(usuarioRepository).save(any(Usuario.class));
        verify(usuarioMapper).toResponseDto(usuarioSalvo);
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Lança exceção quando o usuário for nulo")
    void deveLancarExcecaoQuandoRequestForNulo() {
        doThrow(new UsuarioValidationException("Usuário nulo: digite as informações do usuário!"))
                .when(usuarioValidator).validaCamposDoUsuario(null);

        assertThrows(UsuarioValidationException.class,
                () -> usuarioService.adicionarUsuario(null));

        verify(usuarioValidator).validaCamposDoUsuario(null);
        verifyNoInteractions(usuarioMapper);
        verifyNoInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Lança exceção quando alguma validação dos campos falhar")
    void deveLancarExcecaoQuandoValidacaoDeCamposFalhar() {
        doThrow(new UsuarioValidationException("Campos inválidos!"))
                .when(usuarioValidator).validaCamposDoUsuario(request);

        assertThrows(UsuarioValidationException.class,
                () -> usuarioService.adicionarUsuario(request));

        verify(usuarioValidator).validaCamposDoUsuario(request);
        verifyNoInteractions(usuarioMapper);
        verifyNoInteractions(usuarioRepository);
    }
}