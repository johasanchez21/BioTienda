package com.example.BioTienda.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Pedido")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    private int id;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "total", nullable = false)
    private Number total;
    @Column(name = "id_usuario", nullable = false)
    private int id_usuario;
}
