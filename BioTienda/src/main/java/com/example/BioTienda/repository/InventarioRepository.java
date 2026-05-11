package com.example.BioTienda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioTienda.entity.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByTiendaId(Long tiendaId);

    List<Inventario> findByProductoId(Long productoId);

    Optional<Inventario> findByProductoIdAndTiendaId(Long productoId, Long tiendaId);

    boolean existsByProductoIdAndTiendaId(Long productoId, Long tiendaId);
}
