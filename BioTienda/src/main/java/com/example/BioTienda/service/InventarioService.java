package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.InventarioDTO;

public interface InventarioService {
    
    InventarioDTO.Response crear(InventarioDTO.Request request);

    InventarioDTO.Response agregarStock(Long productoId, Long tiendaId, Integer cantidad);

    InventarioDTO.Response reducirStock(Long productoId, Long tiendaId, Integer cantidad);

    InventarioDTO.Response consultarStock(Long productoId, Long tiendaId);

    List<InventarioDTO.Response> obtenerInventarioPorTienda(Long tiendaId);

    InventarioDTO.Response actualizarCantidad(Long inventarioId, Integer cantidad);
}
