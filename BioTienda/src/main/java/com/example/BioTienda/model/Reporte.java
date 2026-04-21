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
@Table(name = "Reporte")
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {
    @Id
    private int id;
    @Column(name = "tipo", nullable = false)
    private String tipo;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "datos", nullable = false)
    private String datos;
}
