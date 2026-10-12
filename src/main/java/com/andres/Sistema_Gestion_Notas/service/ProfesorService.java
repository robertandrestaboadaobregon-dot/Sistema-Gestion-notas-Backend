package com.andres.Sistema_Gestion_Notas.service;

import com.andres.Sistema_Gestion_Notas.dto.request.ProfesorRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.ProfesorResponse;

import java.util.List;

public interface ProfesorService {
    List<ProfesorResponse> listar();
    ProfesorResponse obtenerPorId(Long id);
    ProfesorResponse crear(ProfesorRequest request);
    ProfesorResponse actualizar(Long id,ProfesorRequest request);
    void eliminar(Long id);
}
