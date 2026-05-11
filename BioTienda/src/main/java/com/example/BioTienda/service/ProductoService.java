package com.example.BioTienda.service;

import java.util.List;

import com.example.BioTienda.dto.ProductoDTO;
import com.example.BioTienda.entity.Producto;

public interface ProductoService {
    List<ProductoDTO.Response> listarTodos();

    ProductoDTO.Response buscarPorId(Long id);

    ProductoDTO.Response crear(ProductoDTO.Request request);

    ProductoDTO.Response actualizar(Long id, ProductoDTO.Request request);

    Producto desactivarProducto(Long id);

    Producto activarProducto(Long id);

    void eliminar(Long id);

}
