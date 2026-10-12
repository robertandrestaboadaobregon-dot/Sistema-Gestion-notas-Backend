package com.andres.Sistema_Gestion_Notas.dto.response;

import java.time.LocalDate;

public record MatriculaResponse(
        Long id,
        AlumnoResponse alumno,
        CursoResponse curso,
        LocalDate fechaMatricula,
        String estado

) {
}
