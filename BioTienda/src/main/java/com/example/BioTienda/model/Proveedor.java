package com.example.BioTienda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Proveedor")
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
    @Id
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "contacto", nullable = false)
    private String contacto;
}
