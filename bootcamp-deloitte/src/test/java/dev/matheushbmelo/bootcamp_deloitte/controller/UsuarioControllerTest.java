package dev.matheushbmelo.bootcamp_deloitte.controller;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.service.UsuarioService;
import dev.matheushbmelo.bootcamp_deloitte.service.exception.UsuarioNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    UsuarioResponseDto usuarioResponseDto;
    UsuarioResponseDto usuarioEditado;

    @BeforeEach
    void setUp() {
        usuarioResponseDto = new UsuarioResponseDto(1L, "Matheus", "matheus@email.com", "123.456.789.01", "81999999999");
        usuarioEditado = new UsuarioResponseDto(1L, "Matheus Barbosa", "matheus@email.com", "123", "819");
    }

    @Test
    @DisplayName("Cria novo usuário")
    void deveCriarUsuarioComSucesso() throws Exception {
        when(usuarioService.adicionarUsuario(any())).thenReturn(usuarioResponseDto);

        String bodyJson = """
                        {
                            "nome": "Matheus",
                            "email": "matheus@email.com",
                            "cpf": "123.456.789.01",
                            "telefone": "81999999999"
                        }
                """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Matheus"))
                .andExpect(jsonPath("$.email").value("matheus@email.com"))
                .andExpect(jsonPath("$.cpf").value("123.456.789.01"))
                .andExpect(jsonPath("$.telefone").value("81999999999"));
    }

    @Test
    @DisplayName("Busca usuário por ID")
    void deveBuscarUsuarioPorIdComSucesso() throws Exception {
        when(usuarioService.listarUsuario(1L)).thenReturn(usuarioResponseDto);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Matheus"))
                .andExpect(jsonPath("$.email").value("matheus@email.com"))
                .andExpect(jsonPath("$.cpf").value("123.456.789.01"))
                .andExpect(jsonPath("$.telefone").value("81999999999"));
    }

    @Test
    @DisplayName("Retorna 404 ao buscar usuário inexistente")
    void deveRetornarExcecaoQuandoUsuarioNaoExiste() throws Exception {
        when(usuarioService.listarUsuario(1L))
                .thenThrow(new UsuarioNotFoundException("Usuario de ID 1 não encontrado"));

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Lista todos os usuários")
    void deveListarUsuariosComSucesso() throws Exception {
        List<UsuarioResponseDto> usuarios = List.of(
                usuarioResponseDto,
                new UsuarioResponseDto(2L, "Renato", "renato@email.com", "987.654.321.01", "81988888888")
        );

        when(usuarioService.listarTodosUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    @DisplayName("Deleta usuário por ID")
    void deveDeletarUsuario() throws Exception {
        doNothing().when(usuarioService).removerUsuario(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Retorna 404 ao deletar usuário inexistente")
    void deveRetornarExcecaoQuandoTentarDeletarUsuarioNaoExiste() throws Exception {
        doThrow(new UsuarioNotFoundException("Usuário de ID 1 não encontrado"))
                .when(usuarioService).removerUsuario(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Atualiza usuário por ID")
    void deveAtualizarUsuarioComSucesso() throws Exception {
        when(usuarioService.editarUsuario(eq(1L), any())).thenReturn(usuarioEditado);

        String bodyJson = """
                    {
                        "nome": "Matheus Barbosa",
                        "email": "matheus@email.com",
                        "cpf": "123",
                        "telefone": "819"
                    }
                """;

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Matheus Barbosa"))
                .andExpect(jsonPath("$.email").value("matheus@email.com"))
                .andExpect(jsonPath("$.cpf").value("123"))
                .andExpect(jsonPath("$.telefone").value("819"));
    }

    @Test
    @DisplayName("Retorna 404 ao editar usuário inexistente")
    void deveRetornarExcecaoQuandoTentarEditarUsuarioNaoExiste() throws Exception {
        when(usuarioService.editarUsuario(eq(1L), any()))
                .thenThrow(new UsuarioNotFoundException("Usuario de ID 1 não encontrado"));

        String bodyJson = """
                    {
                        "nome": "Matheus Barbosa",
                        "email": "matheus@email.com",
                        "cpf": "123",
                        "telefone": "819"
                    }
                """;

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isNotFound());
    }
}