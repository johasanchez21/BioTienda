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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BioTienda.dto.EnvioDTO;
import com.example.BioTienda.service.EnvioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<EnvioDTO.Response>> listarTodos() {

        log.debug("GET /api/envios");

        return ResponseEntity.ok(envioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO.Response> buscarPorId(@PathVariable Long id) {

        log.debug("GET /api/envios/{}", id);

        return ResponseEntity.ok(envioService.buscarPorId(id));
    }

    @GetMapping("/pedidos/{pedidoId}")
    public ResponseEntity<List<EnvioDTO.Response>> buscarPorPedido(
            @PathVariable Long pedidoId) {

        log.debug("GET /api/envios/pedidos/{}", pedidoId);

        return ResponseEntity.ok(envioService.buscarPorPedido(pedidoId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EnvioDTO.Response>> buscarPorEstado(
            @PathVariable String estado) {

        log.debug("GET /api/envios/estado/{}", estado);

        return ResponseEntity.ok(envioService.buscarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<EnvioDTO.Response> crear(
            @Valid @RequestBody EnvioDTO.Request request) {

        log.debug("POST /api/envios - body: {}", request);

        EnvioDTO.Response creado = envioService.crear(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<EnvioDTO.Response> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {

        log.debug("PUT /api/envios/{}/estado?estado={}", id, estado);

        return ResponseEntity.ok(envioService.actualizarEstado(id, estado));
    }

    @GetMapping("/{id}/estado-externo")
    public ResponseEntity<EnvioDTO.Response> consultarEstadoExterno(
            @PathVariable Long id) {

        log.debug("GET /api/envios/{}/estado-externo", id);

        return ResponseEntity.ok(envioService.consultarEstadoExterno(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        log.debug("DELETE /api/envios/{}", id);

        envioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
