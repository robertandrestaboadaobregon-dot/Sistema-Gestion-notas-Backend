package com.andres.Sistema_Gestion_Notas.dto.response;

import java.time.LocalDate;

public record AsistenciaResponse(
        Long id,
        Long matriculaId,
        LocalDate fecha,
        String estado,
        String observacion
) {
}
