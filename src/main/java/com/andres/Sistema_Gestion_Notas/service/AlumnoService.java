package com.andres.Sistema_Gestion_Notas.service;

import com.andres.Sistema_Gestion_Notas.dto.request.AlumnoRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AlumnoResponse;

import java.util.List;

public interface AlumnoService {
    List<AlumnoResponse> listar();
    AlumnoResponse obtenerPorId(Long id);
    AlumnoResponse crear(AlumnoRequest request);
    AlumnoResponse actualizar(Long id,AlumnoRequest request);
    // un eliminado logico viene a ser que no ser va eliminar directamente de la base de datos
    // si no solo se va desactivar osea que va a tener un tipo de dato booleano true o false
    void eliminar(Long id);
}
