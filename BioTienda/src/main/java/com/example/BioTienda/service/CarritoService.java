package com.example.BioTienda.service;

import com.example.BioTienda.dto.CarritoDTO;

public interface CarritoService {
    CarritoDTO.Response crear(Long usuarioId);

    CarritoDTO.Response obtenerCarritoPorUsuario(Long usuarioId);

    CarritoDTO.Response obtenerCarritoPorId(Long carritoId);

    void eliminarCarrito(Long carritoId);
}
