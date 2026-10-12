package com.andres.Sistema_Gestion_Notas.service;

import com.andres.Sistema_Gestion_Notas.dto.request.CursoRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.CursoResponse;

import java.util.List;

public interface CursoService {
    List<CursoResponse> listar();
    CursoResponse obtenerPorId(Long id);
    CursoResponse crear(CursoRequest request);
    CursoResponse actualizar(Long id, CursoRequest request);
    void eliminar(Long id);
}
