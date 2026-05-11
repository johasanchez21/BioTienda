package com.example.BioTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BioTienda.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
        boolean existsByNombreIgnoreCase(String nombre);

        Optional<Producto> findByNombre(String nombre);

}
