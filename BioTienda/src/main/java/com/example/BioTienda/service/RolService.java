package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.RolDTO;
import com.example.BioTienda.entity.Rol;

public interface RolService {
    List<RolDTO.Response> listarTodos();

    RolDTO.Response buscarPorId(Long id);

    RolDTO.Response crear(RolDTO.Request request);

    RolDTO.Response actualizar(Long id, RolDTO.Request request);

    Rol asignarPermisos(Long rolId, List<Long> permisosIds);

    void eliminar(Long id);
}
