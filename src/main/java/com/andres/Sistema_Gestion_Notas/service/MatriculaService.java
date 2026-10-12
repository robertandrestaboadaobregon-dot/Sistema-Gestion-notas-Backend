package com.andres.Sistema_Gestion_Notas.service;

import com.andres.Sistema_Gestion_Notas.dto.request.MatriculaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.MatriculaResponse;
import com.andres.Sistema_Gestion_Notas.dto.response.ResumenAcademicoResponse;

import java.util.List;

public interface MatriculaService {
    List<MatriculaResponse> listar();
    MatriculaResponse obtenerPorId(Long id);
    MatriculaResponse crear(MatriculaRequest request);
    void eliminar(Long id);
    List<MatriculaResponse> listarPorAlumno(Long alumnoId);
    List<MatriculaResponse> listarPorCurso(Long cursoId);
    ResumenAcademicoResponse obtenerResumen(Long matriculaId);
}
