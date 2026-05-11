package com.example.BioTienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.dto.CarritoItemDTO;
import com.example.BioTienda.entity.Carrito;
import com.example.BioTienda.entity.CarritoItem;
import com.example.BioTienda.entity.Producto;
import com.example.BioTienda.repository.CarritoItemRepository;
import com.example.BioTienda.repository.CarritoRepository;
import com.example.BioTienda.repository.ProductoRepository;
import com.example.BioTienda.service.CarritoItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarritoItemServiceImpl implements CarritoItemService {

    private final CarritoItemRepository carritoItemRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    @Override
    public CarritoItemDTO.Response agregarProducto(Long carritoId, Long productoId, Integer cantidad) {
        log.info("Agregando producto id: {} al carrito id: {}", productoId, carritoId);

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> {
                    log.error("Carrito no encontrado con id: {}", carritoId);
                    return new RuntimeException("Carrito no encontrado con id: " + carritoId);
                });

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con id: {}", productoId);
                    return new RuntimeException("Producto no encontrado con id: " + productoId);
                });

        CarritoItem item = carritoItemRepository
                .findByCarritoIdAndProductoId(carritoId, productoId)
                .orElse(null);

        if (item != null) {
            log.info("Producto ya existe en carrito. Aumentando cantidad");

            item.setCantidad(item.getCantidad() + cantidad);

            return mapToResponse(carritoItemRepository.save(item));
        }

        item = new CarritoItem();
        item.setCarrito(carrito);
        item.setProducto(producto);
        item.setCantidad(cantidad);
        item.setPrecio(producto.getPrecio());

        CarritoItem guardado = carritoItemRepository.save(item);

        log.info("Item agregado con id: {}", guardado.getId());

        return mapToResponse(guardado);
    }

    @Override
    public CarritoItemDTO.Response actualizarCantidad(Long carritoItemId, Integer cantidad) {
        log.info("Actualizando cantidad del item id: {}", carritoItemId);

        CarritoItem item = carritoItemRepository.findById(carritoItemId)
                .orElseThrow(() -> {
                    log.error("Item no encontrado con id: {}", carritoItemId);
                    return new RuntimeException("Item no encontrado con id: " + carritoItemId);
                });

        item.setCantidad(cantidad);

        CarritoItem actualizado = carritoItemRepository.save(item);

        log.info("Cantidad actualizada para item id: {}", actualizado.getId());

        return mapToResponse(actualizado);
    }

    @Override
    public void eliminarProducto(Long carritoItemId) {
        log.info("Eliminando item del carrito con id: {}", carritoItemId);

        CarritoItem item = carritoItemRepository.findById(carritoItemId)
                .orElseThrow(() -> {
                    log.error("Item no encontrado con id: {}", carritoItemId);
                    return new RuntimeException("Item no encontrado con id: " + carritoItemId);
                });

        carritoItemRepository.delete(item);

        log.info("Item eliminado con id: {}", carritoItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarritoItemDTO.Response> obtenerItemsCarrito(Long carritoId) {
        log.info("Listando items del carrito id: {}", carritoId);

        return carritoItemRepository.findByCarritoId(carritoId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    

    @Override
    @Transactional
    public void vaciarCarrito(Long carritoId) {
        log.info("Vaciando items del carrito id: {}", carritoId);

        carritoItemRepository.deleteByCarritoId(carritoId);

        log.info("Carrito vaciado correctamente. Carrito id: {}", carritoId);
    }

    private CarritoItemDTO.Response mapToResponse(CarritoItem item) {
        return new CarritoItemDTO.Response(
                item.getId(),
                item.getCantidad(),
                item.getProducto().getId(),
                item.getProducto().getNombre(),
                item.getProducto().getPrecio(),
                item.getCarrito().getId()
        );
    }
}