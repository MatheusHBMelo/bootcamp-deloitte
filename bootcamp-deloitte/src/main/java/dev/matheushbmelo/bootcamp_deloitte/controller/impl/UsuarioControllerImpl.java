package dev.matheushbmelo.bootcamp_deloitte.controller.impl;

import dev.matheushbmelo.bootcamp_deloitte.controller.UsuarioController;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioRequestDto;
import dev.matheushbmelo.bootcamp_deloitte.controller.dto.UsuarioResponseDto;
import dev.matheushbmelo.bootcamp_deloitte.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControllerImpl implements UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioControllerImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> createUser(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.adicionarUsuario(usuarioRequestDto));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.listarUsuario(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.listarTodosUsuarios());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.usuarioService.removerUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponseDto> updateById(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.editarUsuario(id, usuarioRequestDto));
    }
}
