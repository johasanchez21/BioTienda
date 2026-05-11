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

import com.example.BioTienda.dto.ProductoDTO;
import com.example.BioTienda.entity.Producto;
import com.example.BioTienda.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO.Response>> listarTodos() {
        log.debug("GET /api/productos");
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO.Response> buscarPorId(@PathVariable Long id) {
        log.debug("GET /api/productos/{}", id);
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO.Response> crear(@Valid @RequestBody ProductoDTO.Request request) {
        log.debug("POST /api/productos - body: {}", request);
        ProductoDTO.Response creado = productoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO.Response> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO.Request request) {
        log.debug("PUT /api/productos{}", id);
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.debug("DELETE /api/productos{}", id);
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desactivar")
        public Producto desactivarProducto(@PathVariable Long id) {
        return productoService.desactivarProducto(id);
    }

    @PutMapping("/{id}/activar")
    public Producto activarProducto(@PathVariable Long id) {

        return productoService.activarProducto(id);
    }
}
