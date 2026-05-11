package com.example.BioTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BioTienda.entity.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioId(Long usuarioId);

}
