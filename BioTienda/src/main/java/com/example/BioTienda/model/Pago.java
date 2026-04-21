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
@Table(name = "Pago")
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    @Id
    private int id;
    @Column(name = "monto", nullable = false)
    private Number monto;
    @Column(name = "metodo", nullable = false)
    private String metodo;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "id_pedido", nullable = false)
    private int id_pedido;
}
