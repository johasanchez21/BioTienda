package com.example.BioTienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BioTienda.entity.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    List<Envio> findByPedidoId(Long pedidoId);

    List<Envio> findByEstado(String estado);

    boolean existsByCodigoSeguimientoIgnoreCase(String codigoSeguimiento);
}
