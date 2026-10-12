package com.andres.Sistema_Gestion_Notas.service;

import com.andres.Sistema_Gestion_Notas.dto.request.AsistenciaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AsistenciaResponse;

import java.util.List;

public interface AsistenciaService {
    List<AsistenciaResponse> listarPorMatricula(Long matriculaId);
    AsistenciaResponse crear(AsistenciaRequest request);
    AsistenciaResponse actualizar(Long id,AsistenciaRequest request);
    void eliminar(Long id);

}
