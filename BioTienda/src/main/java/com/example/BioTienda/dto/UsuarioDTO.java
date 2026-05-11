package com.example.BioTienda.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UsuarioDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        @NotBlank(message = "El RUT es obligatorio")
        @Size(min = 10, max = 12, message = "El RUT debe tener entre 10 y 12 caracteres")
        private String rut;

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Formato de email inválido")
        private String email;

        @NotBlank(message = "El password es obligatorio")
        private String password;

        private boolean activo = true;

        @NotNull(message = "El ID del Rol es obligatorio")
        private Long rolId;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
            private Long id;
            private String rut;
            private String nombre;
            private String email;
            private String password;
            private Boolean activo;
            private RolDTO.Response rol;

            public Response(Long id, String rut, String nombre,
                    String email, Boolean activo,
                    RolDTO.Response rol) {

                this.id = id;
                this.rut = rut;
                this.nombre = nombre;
                this.email = email;
                this.activo = activo;
                this.rol = rol;
            }
    }
}
