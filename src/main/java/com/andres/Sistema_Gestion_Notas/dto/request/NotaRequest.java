package com.andres.Sistema_Gestion_Notas.dto.request;

import com.andres.Sistema_Gestion_Notas.enums.TipoEvaluacion;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaRequest(
        @NotNull(message = "la matricula es obligatoria") Long matriculaId,
        @NotNull(message = "El tipo de evaluacion es obligatorio")TipoEvaluacion tipoEvaluacion,
        @NotNull(message = "El valor es obligatorio")
        @DecimalMin(value = "0.0", message = "la nota minima es 0")
        @DecimalMax(value = "20.0",message = "la nota maxima es 20")
        BigDecimal valor,
        @DecimalMin(value = "0.0",message = "El peso no puede ser negativo") BigDecimal peso,
        LocalDate fecha,
        String observacion
        ) {
}
