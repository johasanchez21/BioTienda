package com.example.BioTienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.dto.InventarioDTO;
import com.example.BioTienda.entity.Inventario;
import com.example.BioTienda.entity.Producto;
import com.example.BioTienda.entity.Tienda;
import com.example.BioTienda.repository.InventarioRepository;
import com.example.BioTienda.repository.ProductoRepository;
import com.example.BioTienda.repository.TiendaRepository;
import com.example.BioTienda.service.InventarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;
    private final TiendaRepository tiendaRepository;

    @Override
    public InventarioDTO.Response crear(InventarioDTO.Request request) {
        log.info("Creando inventario para producto id: {} y tienda id: {}",
                request.getProductoId(), request.getTiendaId());

        if (inventarioRepository.existsByProductoIdAndTiendaId(
                request.getProductoId(), request.getTiendaId())) {

            log.error("Ya existe inventario para producto id: {} y tienda id: {}",
                    request.getProductoId(), request.getTiendaId());

            throw new RuntimeException("Ya existe inventario para este producto en esta tienda");
        }

        Producto producto = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con id: {}", request.getProductoId());
                    return new RuntimeException("Producto no encontrado con id: " + request.getProductoId());
                });

        Tienda tienda = tiendaRepository.findById(request.getTiendaId())
                .orElseThrow(() -> {
                    log.error("Tienda no encontrada con id: {}", request.getTiendaId());
                    return new RuntimeException("Tienda no encontrada con id: " + request.getTiendaId());
                });

        Inventario inventario = new Inventario();
        inventario.setProducto(producto);
        inventario.setTienda(tienda);
        inventario.setCantidad(request.getCantidad());

        Inventario guardado = inventarioRepository.save(inventario);

        log.info("Inventario creado con id: {}", guardado.getId());

        return mapToResponse(guardado);
    }

    @Override
    public InventarioDTO.Response agregarStock(Long productoId, Long tiendaId, Integer cantidad) {
        log.info("Agregando stock. Producto id: {}, Tienda id: {}, Cantidad: {}",
                productoId, tiendaId, cantidad);

        Inventario inventario = inventarioRepository
                .findByProductoIdAndTiendaId(productoId, tiendaId)
                .orElseThrow(() -> {
                    log.error("Inventario no encontrado para producto id: {} y tienda id: {}",
                            productoId, tiendaId);
                    return new RuntimeException("Inventario no encontrado");
                });

        inventario.setCantidad(inventario.getCantidad() + cantidad);

        return mapToResponse(inventarioRepository.save(inventario));
    }

    @Override
    public InventarioDTO.Response reducirStock(Long productoId, Long tiendaId, Integer cantidad) {
        log.info("Reduciendo stock. Producto id: {}, Tienda id: {}, Cantidad: {}",
                productoId, tiendaId, cantidad);

        Inventario inventario = inventarioRepository
                .findByProductoIdAndTiendaId(productoId, tiendaId)
                .orElseThrow(() -> {
                    log.error("Inventario no encontrado para producto id: {} y tienda id: {}",
                            productoId, tiendaId);
                    return new RuntimeException("Inventario no encontrado");
                });

        if (inventario.getCantidad() < cantidad) {
            log.error("Stock insuficiente. Stock actual: {}, Cantidad solicitada: {}",
                    inventario.getCantidad(), cantidad);

            throw new RuntimeException("Stock insuficiente");
        }

        inventario.setCantidad(inventario.getCantidad() - cantidad);

        return mapToResponse(inventarioRepository.save(inventario));
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioDTO.Response consultarStock(Long productoId, Long tiendaId) {
        log.info("Consultando stock. Producto id: {}, Tienda id: {}", productoId, tiendaId);

        Inventario inventario = inventarioRepository
                .findByProductoIdAndTiendaId(productoId, tiendaId)
                .orElseThrow(() -> {
                    log.error("Inventario no encontrado para producto id: {} y tienda id: {}",
                            productoId, tiendaId);
                    return new RuntimeException("Inventario no encontrado");
                });

        return mapToResponse(inventario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioDTO.Response> obtenerInventarioPorTienda(Long tiendaId) {
        log.info("Obteniendo inventario de tienda id: {}", tiendaId);

        return inventarioRepository.findByTiendaId(tiendaId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventarioDTO.Response actualizarCantidad(Long inventarioId, Integer cantidad) {
        log.info("Actualizando cantidad del inventario id: {}", inventarioId);

        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> {
                    log.error("Inventario no encontrado con id: {}", inventarioId);
                    return new RuntimeException("Inventario no encontrado con id: " + inventarioId);
                });

        inventario.setCantidad(cantidad);

        Inventario actualizado = inventarioRepository.save(inventario);

        log.info("Inventario actualizado con id: {}", actualizado.getId());

        return mapToResponse(actualizado);
    }

    private InventarioDTO.Response mapToResponse(Inventario inventario) {
        return new InventarioDTO.Response(
                inventario.getId(),
                inventario.getCantidad(),
                inventario.getProducto().getId(),
                inventario.getProducto().getNombre(),
                inventario.getTienda().getId(),
                inventario.getTienda().getNombre()
        );
    }
}
                
