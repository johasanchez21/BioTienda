package com.example.BioTienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.dto.ProductoDTO;
import com.example.BioTienda.entity.Producto;
import com.example.BioTienda.repository.ProductoRepository;
import com.example.BioTienda.service.ProductoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO.Response> listarTodos() {
        log.info("Listando todos los productos");
        return productoRepository.findAll()
        .stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO.Response buscarPorId(Long id) {
        log.info("Buscando producto con id: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con id: {}", id);
                    return new RuntimeException("Producto no encontrado con id: " + id);
                });
        return mapToResponse(producto);
    }

    @Override
    @Transactional
    public ProductoDTO.Response crear(ProductoDTO.Request request){
        log.info("Creando nuevo producto: {}", request.getNombre());

        if (productoRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RuntimeException("Ya existe un producto con ese nombre: " + request.getNombre());
        }

        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        Producto guardado = productoRepository.save(producto);
        log.info("Producto creado con id: {}", guardado.getId());

        return mapToResponse(guardado);

    }

    @Override
    @Transactional
    public ProductoDTO.Response actualizar(Long id, ProductoDTO.Request request){
        log.info("Actualizando Producto con id: {}", id);

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id:" + id));
        
        producto.setNombre(request.getNombre());
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        Producto actualizado = productoRepository.save(producto);

        log.info("Producto actualizado: {}", actualizado.getId());
        return mapToResponse(actualizado);

    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.info("Eliminando Producto con id: {}", id);

        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id:" + id);
        }

        productoRepository.deleteById(id);
        log.info("Producto eliminado con id: {}", id);
    }

    public Producto desactivarProducto(Long id) {

            Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.setActivo(false);

            return productoRepository.save(producto);
    }

    @Override
        public Producto activarProducto(Long id) {

            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.setActivo(true);

            return productoRepository.save(producto);
    }

    private ProductoDTO.Response mapToResponse(Producto producto) {
        return new ProductoDTO.Response(producto.getId(),producto.getNombre(),
        producto.getDescripcion(),producto.getPrecio(),producto.getStock()
        ,producto.getActivo());
    }
    
}
