package com.example.BioTienda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CarritoDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long id;
        private Long usuarioId;
        private String usuarioNombre;
    }
}
