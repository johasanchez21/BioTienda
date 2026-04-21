package com.example.BioTienda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Tienda")
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {
    @Id
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "direccion", nullable = false)
    private String direccion;
    @Column(name = "ciudad", nullable = false)
    private String ciudad;
    @Column(name = "horario",nullable = false)
    private String horario;
}
