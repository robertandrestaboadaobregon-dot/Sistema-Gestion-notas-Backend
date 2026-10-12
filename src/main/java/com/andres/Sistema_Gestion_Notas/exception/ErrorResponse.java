package com.andres.Sistema_Gestion_Notas.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        LocalDateTime tiempo_exacto,
        int estado,
        String error,
        String mensaje,
        Map<String,String> campos


) {
}
