package com.example.BioTienda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Rol")
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    private int id;
    @Column(name = "rol", nullable = false)
    private String rol;
}
