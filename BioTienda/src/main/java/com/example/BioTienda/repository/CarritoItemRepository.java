package com.example.BioTienda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioTienda.entity.CarritoItem;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
 
    List<CarritoItem> findByCarritoId(Long carritoId);

    Optional<CarritoItem> findByCarritoIdAndProductoId(Long carritoId, Long productoId);

    boolean existsByCarritoIdAndProductoId(Long carritoId, Long productoId);

    void deleteByCarritoId(Long carritoId);
}
