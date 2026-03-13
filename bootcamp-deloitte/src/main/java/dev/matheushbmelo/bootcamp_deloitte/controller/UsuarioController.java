package dev.matheushbmelo.bootcamp_deloitte.controller;

import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Essa interface do controller mapeia os métodos que são implementados no UsuarioControllerImpl
// Essa interface também mapeia as anotações do Swagger
@Tag(name = "Usuario Controller", description = "Funções CRUD para usuário")
public interface UsuarioController {
    @Operation(summary = "Create", description = "Cria um novo usuário e salva no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Erro a tentar criar usuário", content = @Content),
    })
    @PostMapping
    ResponseEntity<UsuarioResponseDto> createUser(@RequestBody UsuarioRequestDto usuarioRequestDto);

    @Operation(summary = "FindById", description = "Busca um usuário no banco de dados via ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(path = "/{id}")
    ResponseEntity<UsuarioResponseDto> findById(@Parameter(description = "ID do usuário", required = true, example = "1", schema = @Schema(type = "integer", format = "int64")) @PathVariable Long id);

    @Operation(summary = "FindAll", description = "Busca todos os usuários no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))))
    })
    @GetMapping
    ResponseEntity<List<UsuarioResponseDto>> findAll();

    @Operation(summary = "DeleteById", description = "Deleta usuário no banco de dados via ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para deletar", content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deleteById(@Parameter(description = "ID do usuário", required = true, example = "1",
            schema = @Schema(type = "integer", format = "int64")) @PathVariable Long id);

    @Operation(summary = "UpdateById", description = "Atualiza usuário no banco de dados via ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualizar", content = @Content)
    })
    @PutMapping(path = "/{id}")
    ResponseEntity<UsuarioResponseDto> updateById(@Parameter(description = "ID do usuário", required = true, example = "1",
            schema = @Schema(type = "integer", format = "int64")) @PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto);
}
