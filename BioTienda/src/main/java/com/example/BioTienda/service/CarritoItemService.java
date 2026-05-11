package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.CarritoItemDTO;

public interface CarritoItemService {
    
    CarritoItemDTO.Response agregarProducto(
            Long carritoId,
            Long productoId,
            Integer cantidad);

    CarritoItemDTO.Response actualizarCantidad(
            Long carritoItemId,
            Integer cantidad);

    void eliminarProducto(Long carritoItemId);

    List<CarritoItemDTO.Response> obtenerItemsCarrito(Long carritoId);

    void vaciarCarrito(Long carritoId);
}
