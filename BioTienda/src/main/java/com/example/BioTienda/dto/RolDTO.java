package com.example.BioTienda.dto;

import java.util.List;

import com.example.BioTienda.entity.Permiso;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RolDTO {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre del rol es obligatorio")
        private String nombre;

        private List<Permiso> permisos;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String nombre;
        List<PermisoDTO.Response> permisos;
    }
}
