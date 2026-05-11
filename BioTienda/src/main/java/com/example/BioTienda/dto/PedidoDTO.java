package com.example.BioTienda.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PedidoDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
    
        @NotNull(message = "El total es obligatorio")
        @Positive(message = "El total debe ser mayor a 0")
        @Column(nullable = false)
        private Integer total;

        @Column(nullable = false, length = 50)
        private String estado;

        @Column(nullable = false)
        private Date fecha;

        private Long usuarioId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Integer total;
        private String estado;
        private Date fecha;
        private Long usuarioId;
        private String UsuarioNombre;
    }
}
