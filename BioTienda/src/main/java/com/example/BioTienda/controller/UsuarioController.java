package com.example.BioTienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BioTienda.dto.UsuarioDTO;
import com.example.BioTienda.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO.Response>> listarTodos() {
        log.debug("GET /api/usuarios");
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO.Response> buscarPorId(@PathVariable Long id) {
        log.debug("GET /api/usuarios/{}", id);
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<UsuarioDTO.Response> buscarPorRut(@PathVariable String rut) {
        log.debug("GET /api/usuarios/rut/{}", rut);
        return ResponseEntity.ok(usuarioService.buscarPorRut(rut));
    }

    @GetMapping("/genero/{generoId}")
    public ResponseEntity<List<UsuarioDTO.Response>> buscarPorRol(@PathVariable Long rolId) {
        log.debug("GET /api/usuarios/rol/{}", rolId);
        return ResponseEntity.ok(usuarioService.buscarPorRol(rolId));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO.Response> crear(@Valid @RequestBody UsuarioDTO.Request request) {
        log.debug("POST /api/usuarios - RUT: {}", request.getRut());
        UsuarioDTO.Response creado = usuarioService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO.Response> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO.Request request) {
        log.debug("PUT /api/usuarios/{}", id);
        return ResponseEntity.ok(usuarioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.debug("DELETE /api/usuarios/{}", id);
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
