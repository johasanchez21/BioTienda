package com.example.BioTienda.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Producto")
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "precio", nullable = false)
    private Number precio;
    @Column(name = "stock", nullable = false)
    private Number stock;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "id_categoria", nullable = false)
    private int id_categoria;
}
