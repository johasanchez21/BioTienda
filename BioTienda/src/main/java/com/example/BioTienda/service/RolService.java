package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.RolDTO;

public interface RolService {
    List<RolDTO.Response> listarTodos();

    RolDTO.Response buscarPorId(Long id);

    RolDTO.Response crear(RolDTO.Request request);

    RolDTO.Response actualizar(Long id, RolDTO.Request request);

    void eliminar(Long id);
}
