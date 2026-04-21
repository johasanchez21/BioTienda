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
@Table(name = "Permiso")
@AllArgsConstructor
@NoArgsConstructor
public class Permiso {
    @Id
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
}
