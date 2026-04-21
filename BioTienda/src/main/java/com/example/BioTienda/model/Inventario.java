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
@Table(name = "Inventario")
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    @Id
    private int id;
    @Column(name = "cantidad", nullable = false)
    private Number cantidad;
    @Column(name = "id_producto", nullable = false)
    private int id_producto;
    @Column(name = "id_tienda", nullable = false)
    private int id_tienda;
}
