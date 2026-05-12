package com.example.BioTienda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EnvioDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El código de seguimiento es obligatorio")
        private String codigoSeguimiento;

        @NotBlank(message = "La empresa es obligatoria")
        private String empresa;

        @NotBlank(message = "El estado es obligatorio")
        private String estado;

        
        private Long pedidoId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String codigoSeguimiento;
        private String empresa;
        private String estado;
        private Long pedidoId;
        private String estadoExterno;
    }
}
