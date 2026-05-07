package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.UsuarioDTO;

public interface UsuarioService {
    
    List<UsuarioDTO.Response> listarTodos();

    UsuarioDTO.Response buscarPorId(Long id);

    UsuarioDTO.Response buscarPorRut(String rut);

    List<UsuarioDTO.Response> buscarPorRol(Long rolId);

    UsuarioDTO.Response crear(UsuarioDTO.Request request);

    UsuarioDTO.Response actualizar(Long id, UsuarioDTO.Request request);

    void eliminar(Long id);
}
