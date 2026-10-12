package com.andres.Sistema_Gestion_Notas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
//no sea null, ni vacio, ni espacios en blanco
public record AlumnoRequest(
        @NotBlank(message = "El codigo es obligatorio") String codigo,
        @NotBlank(message = "Los nombres son obligatorios") String nombres,
        @NotBlank(message = "Los apellidos son obligatorios") String apellidos,
        @NotBlank(message = "El dni es obligatorio") String dni,
        @NotBlank(message = "El email es obligatorio") @Email(message = "el email no es valido") String email,
        LocalDate fechaNacimiento
        ) {}
