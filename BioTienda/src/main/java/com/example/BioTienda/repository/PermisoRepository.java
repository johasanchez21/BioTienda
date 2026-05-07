package com.example.BioTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioTienda.entity.Permiso;


@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long>{
    
    Optional<Permiso> findByNombre(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);
}
