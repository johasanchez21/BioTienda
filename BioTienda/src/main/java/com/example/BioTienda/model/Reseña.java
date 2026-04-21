package com.example.BioTienda.model;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Reseña")
@AllArgsConstructor
@NoArgsConstructor
public class Reseña {
    @Id
    private int id;
    @Column(name = "comentario", nullable = false)
    private String comentario;
    @Column(name = "calificacion", nullable = false)
    private Number calificacion;
    @Column(name = "id_usuario", nullable = false)
    private int id_usuario;
    @Column(name = "id_producto", nullable = false)
    private int id_producto;
}
