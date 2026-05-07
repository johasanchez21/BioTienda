package com.example.BioTienda.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Permiso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotBlank(message = "El nombre del permiso es obligatorio")
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
}
