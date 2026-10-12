package com.andres.Sistema_Gestion_Notas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfesorRequest(
        @NotBlank(message = "El codigo es obligatorio") String codigo,
        @NotBlank(message = "los nombres son obligatorios") String nombres,
        @NotBlank(message = "los apellidos son obligatorios") String apellidos,
        @NotBlank(message = "El email es obligatorio") @Email(message = "el email no es valido") String email,
        String especialidad
) {
}
