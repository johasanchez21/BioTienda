package com.example.BioTienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioTienda.entity.Usuario;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByRut(String rut);

    boolean existsByRut(String rut);

    List<Usuario> findByRolId(Long rolId);
}
