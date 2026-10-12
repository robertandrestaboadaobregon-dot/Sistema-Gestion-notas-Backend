package com.andres.Sistema_Gestion_Notas.dto.request;

import com.andres.Sistema_Gestion_Notas.enums.EstadoAsistencia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

// not null  valida que no sea null
public record AsistenciaRequest(
        @NotNull(message = "la matricula es obligatoria") Long matriculaId,
        @NotNull(message = "la fecha es obligatoria")LocalDate fecha,
        @NotNull(message = "el estado es obligatorio")EstadoAsistencia estado,
        String observacion
        ) {
}
