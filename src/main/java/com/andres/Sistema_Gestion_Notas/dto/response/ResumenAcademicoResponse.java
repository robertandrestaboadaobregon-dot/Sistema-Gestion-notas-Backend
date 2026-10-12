package com.andres.Sistema_Gestion_Notas.dto.response;


import java.math.BigDecimal;

/**
 * Resumen académico de una matrícula: promedio de notas y porcentaje de asistencia
* se usa para el dashboard y para reportes rápidos:
* */
public record ResumenAcademicoResponse(
        Long matriculaId,
        String alumno,
        String curso,
        BigDecimal promedioSimple,
        BigDecimal promedioPonderado,
        Long totalClases,
        Long clasesAsistidas,
        BigDecimal porcentajeAsistencia


) {
}
