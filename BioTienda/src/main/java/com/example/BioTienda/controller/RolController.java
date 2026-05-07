package com.example.BioTienda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BioTienda.dto.RolDTO;
import com.example.BioTienda.service.RolService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {
    
    private final RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolDTO.Response>> listarTodos() {
        log.debug("GET /api/roles");
        return ResponseEntity.ok(rolService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO.Response> buscarPorId(@PathVariable Long id) {
        log.debug("GET /api/roles{}", id);
        return ResponseEntity.ok(rolService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RolDTO.Response> crear(@Valid @RequestBody RolDTO.Request request) {
        log.debug("POST /api/roles - body: {}", request);
        RolDTO.Response creado = rolService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO.Response> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RolDTO.Request request) {
        log.debug("PUT /api/roles{}", id);
        return ResponseEntity.ok(rolService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.debug("DELETE /api/roles{}", id);
        rolService.eliminar(id);
        return ResponseEntity.noContent().build(); 
    }
}
