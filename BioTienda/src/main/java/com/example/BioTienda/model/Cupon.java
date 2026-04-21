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
@Table(name = "Cupon")
@AllArgsConstructor
@NoArgsConstructor
public class Cupon {
    @Id
    private int id;
    @Column(name = "codigo", nullable = false)
    private String codigo;
    @Column(name = "descuento", nullable = false)
    private float descuento;
    @Column(name = "fecha_expiracion", nullable = false)
    private Date fecha_expiracion;
}
