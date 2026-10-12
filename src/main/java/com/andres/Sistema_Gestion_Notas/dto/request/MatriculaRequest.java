package com.andres.Sistema_Gestion_Notas.dto.request;

import jakarta.validation.constraints.NotNull;

public record MatriculaRequest(
        @NotNull(message = "El alumno es obligatorio") Long alumnoId,
        @NotNull(message = "El curso es obligatorio") Long cursoId
) {
}
