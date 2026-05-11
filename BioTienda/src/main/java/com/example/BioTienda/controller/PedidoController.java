package com.example.BioTienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BioTienda.dto.PedidoDTO;
import com.example.BioTienda.service.PedidoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO.Response>> listarTodos() {
        log.debug("GET /api/pedidos");
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO.Response> buscarPorId(@PathVariable Long id) {
        log.debug("GET /api/pedidos/{}", id);
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<PedidoDTO.Response>> buscarPorUsuario(@PathVariable Long usuarioId) {

        log.debug("GET /api/pedidos/usuarios/{}", usuarioId);

        return ResponseEntity.ok(
                pedidoService.buscarPorUsuario(usuarioId)
        );
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PedidoDTO.Response>> buscarPorEstado(
            @PathVariable String estado) {

        log.debug("GET /api/pedidos/estado/{}", estado);

        return ResponseEntity.ok(
                pedidoService.buscarPorEstado(estado)
        );
    }

    @PostMapping
    public ResponseEntity<PedidoDTO.Response> crear(@Valid @RequestBody PedidoDTO.Request request) {
        log.debug("POST /api/pedidos - usuario id: {}", request.getUsuarioId());
        PedidoDTO.Response creado = pedidoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

     @PutMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO.Response> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {

        log.debug("PUT /api/pedidos/{}/estado?estado={}", id, estado);

        return ResponseEntity.ok(pedidoService.actualizarEstado(id, estado));
    }

}
