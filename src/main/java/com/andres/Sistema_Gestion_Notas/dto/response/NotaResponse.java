package com.andres.Sistema_Gestion_Notas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaResponse(
        Long id,
        Long matriculaId,
        String tipoEvaluacion,
        BigDecimal valor,
        BigDecimal peso,
        LocalDate fecha,
        String observacion
) {
}
