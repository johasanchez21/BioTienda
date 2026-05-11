package com.example.BioTienda.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BioTienda.dto.CarritoItemDTO;
import com.example.BioTienda.service.CarritoItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/carrito-items")
@RequiredArgsConstructor
public class CarritoItemController {

    private final CarritoItemService carritoItemService;

    @PostMapping("/carritos/{carritoId}/productos/{productoId}")
    public ResponseEntity<CarritoItemDTO.Response> agregarProducto(
            @PathVariable Long carritoId,
            @PathVariable Long productoId,
            @RequestParam Integer cantidad) {

        log.debug("POST /api/carrito-items/carritos/{}/productos/{}?cantidad={}",
                carritoId, productoId, cantidad);

        CarritoItemDTO.Response creado = carritoItemService.agregarProducto(
                carritoId,
                productoId,
                cantidad
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{carritoItemId}/cantidad")
    public ResponseEntity<CarritoItemDTO.Response> actualizarCantidad(
            @PathVariable Long carritoItemId,
            @RequestParam Integer cantidad) {

        log.debug("PUT /api/carrito-items/{}/cantidad?cantidad={}",
                carritoItemId, cantidad);

        return ResponseEntity.ok(
                carritoItemService.actualizarCantidad(carritoItemId, cantidad)
        );
    }

    @DeleteMapping("/{carritoItemId}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable Long carritoItemId) {

        log.debug("DELETE /api/carrito-items/{}", carritoItemId);

        carritoItemService.eliminarProducto(carritoItemId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/carritos/{carritoId}")
    public ResponseEntity<List<CarritoItemDTO.Response>> obtenerItemsCarrito(
            @PathVariable Long carritoId) {

        log.debug("GET /api/carrito-items/carritos/{}", carritoId);

        return ResponseEntity.ok(
                carritoItemService.obtenerItemsCarrito(carritoId)
        );
    }

    @DeleteMapping("/carritos/{carritoId}")
    public ResponseEntity<Void> vaciarCarrito(
            @PathVariable Long carritoId) {

        log.debug("DELETE /api/carrito-items/carritos/{}", carritoId);

        carritoItemService.vaciarCarrito(carritoId);

        return ResponseEntity.noContent().build();
    }
}