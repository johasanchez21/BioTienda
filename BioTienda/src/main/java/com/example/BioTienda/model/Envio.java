package com.example.BioTienda.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Envio")
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    @Id
    private int id;
    @Column(name = "direccion", nullable = false)
    private String direccion;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "fecha_envio", nullable = false)
    private Date fecha_envio;
    @Column(name = "id_pedido", nullable = false)
    private int id_pedido;
}
