package com.example.BioTienda.model;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Carrito")
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {
    @Id
    private int id;
    @Column(name = "id_usuario", nullable = false)
    private int id_usuario;
}
