package com.example.BioTienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioTienda.entity.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {

    List<Tienda> findByDireccion(String direccion);

    boolean existsByDireccionIgnoreCase(String direccion);

}
