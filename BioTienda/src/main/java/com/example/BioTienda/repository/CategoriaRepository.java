package com.example.BioTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioTienda.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    Optional<Categoria> findByNombre(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);
}
