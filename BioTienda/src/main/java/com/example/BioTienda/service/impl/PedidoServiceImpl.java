package com.example.BioTienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.dto.PedidoDTO;
import com.example.BioTienda.entity.Pedido;
import com.example.BioTienda.entity.Usuario;
import com.example.BioTienda.repository.PedidoRepository;
import com.example.BioTienda.repository.UsuarioRepository;
import com.example.BioTienda.service.PedidoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDTO.Response> listarTodos() {
        log.info("Listando todos los pedidos");
        return pedidoRepository.findAll()
        .stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public PedidoDTO.Response buscarPorId(Long id){
        log.info("Buscando pedidos por id: {}", id);
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Pedido no encontrado con id: {}", id);
                return new RuntimeException("Pedido no encontrado con id:" + id);
            });
        return mapToResponse(pedido);
    }
    @Override
    @Transactional(readOnly = true)
    public List<PedidoDTO.Response> buscarPorUsuario(Long usuarioId) {

        return pedidoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDTO.Response> buscarPorEstado(String estado) {

        log.info("Buscando pedidos con estado: {}", estado);

        return pedidoRepository.findByEstado(estado)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoDTO.Response crear(PedidoDTO.Request request) {

        log.info("Creando nuevo pedido para usuario id: {}", request.getUsuarioId());

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con id: {}", request.getUsuarioId());
                    return new RuntimeException("Usuario no encontrado con id: " + request.getUsuarioId());
                });

        Pedido pedido = new Pedido();
        pedido.setTotal(request.getTotal());
        pedido.setEstado("PENDIENTE");
        pedido.setFecha(request.getFecha());
        pedido.setUsuario(usuario);

        Pedido guardado = pedidoRepository.save(pedido);

        log.info("Pedido creado con id: {}", guardado.getId());

        return mapToResponse(guardado);
    }

    @Override
    @Transactional
    public PedidoDTO.Response actualizarEstado(Long id, String estado) {

        log.info("Actualizando estado del pedido id: {} a {}", id, estado);

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pedido no encontrado con id: {}", id);
                    return new RuntimeException("Pedido no encontrado con id: " + id);
                });

        pedido.setEstado(estado);

        Pedido actualizado = pedidoRepository.save(pedido);

        log.info("Estado actualizado correctamente para pedido id: {}", actualizado.getId());

    return mapToResponse(actualizado);
}

    private PedidoDTO.Response mapToResponse(Pedido pedido) {
        return new PedidoDTO.Response(
              pedido.getId(),
              pedido.getTotal(),
              pedido.getEstado(),
              pedido.getFecha(),
              pedido.getUsuario().getId(),
              pedido.getUsuario().getNombre()
        );
    }
}
