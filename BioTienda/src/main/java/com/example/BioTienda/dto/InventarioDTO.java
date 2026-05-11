package com.example.BioTienda.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InventarioDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a 0")
        @Column(nullable = false)
        private Integer cantidad;
        
        @NotNull(message = "La id del producto es obligatoria")
        private Long productoId;

        @NotNull(message = "La id de la tienda es obligatoria")
        private Long tiendaId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
            private Long id;
            private Integer cantidad;
            private Long productoId;
            private String productoNombre;
            private Long tiendaId;
            private String tiendaNombre;
    }
}
