package com.andres.Sistema_Gestion_Notas.dto.response;

public record ProfesorResponse(
        Long id,
        String codigo,
        String nombres,
        String apellidos,
        String email,
        String especialidad,
        String estado
) {
}
