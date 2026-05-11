package com.example.BioTienda.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.dto.CarritoDTO;
import com.example.BioTienda.entity.Carrito;
import com.example.BioTienda.entity.Usuario;
import com.example.BioTienda.repository.CarritoRepository;
import com.example.BioTienda.repository.UsuarioRepository;
import com.example.BioTienda.service.CarritoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public CarritoDTO.Response crear(Long usuarioId) {
        log.info("Creando carrito para usuario id: {}", usuarioId);

        if (carritoRepository.existsByUsuarioId(usuarioId)) {
            log.error("El usuario ya tiene carrito. Usuario id: {}", usuarioId);
            throw new RuntimeException("El usuario ya tiene un carrito");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con id: {}", usuarioId);
                    return new RuntimeException("Usuario no encontrado con id: " + usuarioId);
                });

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);

        Carrito guardado = carritoRepository.save(carrito);

        log.info("Carrito creado con id: {}", guardado.getId());

        return mapToResponse(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public CarritoDTO.Response obtenerCarritoPorUsuario(Long usuarioId) {
        log.info("Buscando carrito del usuario id: {}", usuarioId);

        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado para usuario id: {}", usuarioId);
                    return new RuntimeException("Carrito no encontrado para usuario id: " + usuarioId);
                });

        return mapToResponse(carrito);
    }

    @Override
    @Transactional(readOnly = true)
    public CarritoDTO.Response obtenerCarritoPorId(Long carritoId) {
        log.info("Buscando carrito con id: {}", carritoId);

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con id: {}", carritoId);
                    return new RuntimeException("Carrito no encontrado con id: " + carritoId);
                });

        return mapToResponse(carrito);
    }

    @Override
    public void eliminarCarrito(Long carritoId) {
        log.info("Eliminando carrito con id: {}", carritoId);

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con id: {}", carritoId);
                    return new RuntimeException("Carrito no encontrado con id: " + carritoId);
                });

        carritoRepository.delete(carrito);

        log.info("Carrito eliminado con id: {}", carritoId);
    }

    /* @Override
    public void vaciarCarrito(Long carritoId) {
        log.info("Vaciando carrito con id: {}", carritoId);

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con id: {}", carritoId);
                    return new RuntimeException("Carrito no encontrado con id: " + carritoId);
                });

        log.info("Carrito encontrado. Pendiente vaciar items cuando CarritoItem esté implementado");
    } */

    private CarritoDTO.Response mapToResponse(Carrito carrito) {
        return new CarritoDTO.Response(
                carrito.getId(),
                carrito.getUsuario().getId(),
                carrito.getUsuario().getNombre()
        );
    }
}
