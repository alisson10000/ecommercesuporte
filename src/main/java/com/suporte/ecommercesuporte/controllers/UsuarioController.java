package com.suporte.ecommercesuporte.controllers;

import com.suporte.ecommercesuporte.dtos.UsuarioDTO;

import com.suporte.ecommercesuporte.services.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        List<UsuarioDTO> usuariosDTO = usuarioService.listarTodos();
        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.buscarPorIdDTO(id);
            return ResponseEntity.ok(usuarioDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuarioDto(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO novoUsuarioDTO = usuarioService.criarUsuarioDTO(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        if (!id.equals(usuarioDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // Atualizar o usuário
            UsuarioDTO usuarioAtualizadoDTO = usuarioService.atualizar(id, usuarioDTO);

            // Atualizar as roles do usuário com os valores em maiúsculas
            usuarioService.atualizarRoles(id, usuarioDTO.getRoles());

            return ResponseEntity.ok(usuarioAtualizadoDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
