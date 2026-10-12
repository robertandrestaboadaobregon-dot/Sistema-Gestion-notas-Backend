package com.andres.Sistema_Gestion_Notas.dto.response;

public record CursoResponse(
        Long id,
        String codigo,
        String nombre,
        Integer creditos,
        ProfesorResponse profesor,
        String estado
) {
}
