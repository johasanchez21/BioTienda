package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.PedidoDTO;

public interface PedidoService {
    List<PedidoDTO.Response> listarTodos();

    PedidoDTO.Response buscarPorId(Long id);

    PedidoDTO.Response crear(PedidoDTO.Request request);

    PedidoDTO.Response actualizarEstado(Long id, String estado);

    List<PedidoDTO.Response> buscarPorUsuario(Long usuarioId);

    List<PedidoDTO.Response> buscarPorEstado(String estado);

}
