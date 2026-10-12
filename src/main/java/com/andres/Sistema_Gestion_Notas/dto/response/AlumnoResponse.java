package com.andres.Sistema_Gestion_Notas.dto.response;

import java.time.LocalDate;

public record AlumnoResponse(
            Long id,
            String codigo,
            String nombres,
            String apellidos,
            String dni,
            String email,
            LocalDate fechaNacimiento,
            String estado

) {
}
