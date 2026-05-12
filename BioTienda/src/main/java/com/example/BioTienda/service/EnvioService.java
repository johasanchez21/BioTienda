package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.EnvioDTO;

public interface EnvioService {

    EnvioDTO.Response crear(EnvioDTO.Request request);

    List<EnvioDTO.Response> listarTodos();

    EnvioDTO.Response buscarPorId(Long id);

    List<EnvioDTO.Response> buscarPorPedido(Long pedidoId);

    List<EnvioDTO.Response> buscarPorEstado(String estado);

    EnvioDTO.Response actualizarEstado(Long id, String estado);

    EnvioDTO.Response consultarEstadoExterno(Long id);

    void eliminar(Long id);
}
