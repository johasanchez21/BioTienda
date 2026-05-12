package com.example.BioTienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.dto.EnvioDTO;
import com.example.BioTienda.entity.Envio;
import com.example.BioTienda.entity.Pedido;
import com.example.BioTienda.repository.EnvioRepository;
import com.example.BioTienda.repository.PedidoRepository;
import com.example.BioTienda.service.EnvioExternoService;
import com.example.BioTienda.service.EnvioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository envioRepository;
    private final PedidoRepository pedidoRepository;
    private final EnvioExternoService envioExternoService;

    @Override
    @Transactional
    public EnvioDTO.Response crear(EnvioDTO.Request request) {

        log.info("Creando envío para pedido id: {}", request.getPedidoId());

        if (envioRepository.existsByCodigoSeguimientoIgnoreCase(request.getCodigoSeguimiento())) {
            log.error("Ya existe un envío con código: {}", request.getCodigoSeguimiento());
            throw new RuntimeException("Ya existe un envío con ese código de seguimiento");
        }

        Pedido pedido = pedidoRepository.findById(request.getPedidoId())
                .orElseThrow(() -> {
                    log.error("Pedido no encontrado con id: {}", request.getPedidoId());
                    return new RuntimeException("Pedido no encontrado con id: " + request.getPedidoId());
                });

        Envio envio = new Envio();
        envio.setCodigoSeguimiento(request.getCodigoSeguimiento());
        envio.setEmpresa(request.getEmpresa());
        envio.setEstado(request.getEstado());
        envio.setPedido(pedido);

        Envio guardado = envioRepository.save(envio);

        log.info("Envío creado con id: {}", guardado.getId());

        return mapToResponse(guardado, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO.Response> listarTodos() {

        log.info("Listando todos los envíos");

        return envioRepository.findAll()
                .stream()
                .map(envio -> mapToResponse(envio, null))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EnvioDTO.Response buscarPorId(Long id) {

        log.info("Buscando envío con id: {}", id);

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Envío no encontrado con id: {}", id);
                    return new RuntimeException("Envío no encontrado con id: " + id);
                });

        return mapToResponse(envio, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO.Response> buscarPorPedido(Long pedidoId) {

        log.info("Buscando envíos del pedido id: {}", pedidoId);

        return envioRepository.findByPedidoId(pedidoId)
                .stream()
                .map(envio -> mapToResponse(envio, null))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO.Response> buscarPorEstado(String estado) {

        log.info("Buscando envíos con estado: {}", estado);

        return envioRepository.findByEstado(estado)
                .stream()
                .map(envio -> mapToResponse(envio, null))
                .collect(Collectors.toList());
    }

    @Override
    public EnvioDTO.Response actualizarEstado(Long id, String estado) {

        log.info("Actualizando estado del envío id: {} a {}", id, estado);

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Envío no encontrado con id: {}", id);
                    return new RuntimeException("Envío no encontrado con id: " + id);
                });

        envio.setEstado(estado);

        Envio actualizado = envioRepository.save(envio);

        log.info("Estado actualizado para envío id: {}", actualizado.getId());

        return mapToResponse(actualizado, null);
    }

    @Override
    @Transactional(readOnly = true)
    public EnvioDTO.Response consultarEstadoExterno(Long id) {

        log.info("Consultando estado externo para envío id: {}", id);

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Envío no encontrado con id: {}", id);
                    return new RuntimeException("Envío no encontrado con id: " + id);
                });

        Map<String, Object> respuestaExterna = envioExternoService.consultarEstadoEnvio();

        String estadoExterno = String.valueOf(respuestaExterna.get("title"));

        return mapToResponse(envio, estadoExterno);
    }

    @Override
    public void eliminar(Long id) {

        log.info("Eliminando envío con id: {}", id);

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Envío no encontrado con id: {}", id);
                    return new RuntimeException("Envío no encontrado con id: " + id);
                });

        envioRepository.delete(envio);

        log.info("Envío eliminado con id: {}", id);
    }

    private EnvioDTO.Response mapToResponse(Envio envio, String estadoExterno) {

        return new EnvioDTO.Response(
                envio.getId(),
                envio.getCodigoSeguimiento(),
                envio.getEmpresa(),
                envio.getEstado(),
                envio.getPedido().getId(),
                estadoExterno
        );
    }
}
