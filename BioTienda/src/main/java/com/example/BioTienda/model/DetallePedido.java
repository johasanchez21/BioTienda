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
@Table(name = "DetallePedido")
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {
    @Id
    private int id;
    @Column(name = "cantidad", nullable = false)
    private Number cantidad;
    @Column(name = "precio_unitario", nullable = false)
    private Number precio_unitario;
    @Column(name = "id_producto", nullable = false)
    private int id_producto;
    @Column(name = "id_pedido", nullable = false)
    private int id_pedido;
}
