package com.andres.Sistema_Gestion_Notas.service;

import com.andres.Sistema_Gestion_Notas.dto.request.NotaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.NotaResponse;

import java.util.List;

public interface NotaService {
    List<NotaResponse> listarPorMatricula(Long matriculaId);
    NotaResponse crear(NotaRequest request);
    NotaResponse actualizar(Long id,NotaRequest request);
    void eliminar(Long id);
}
