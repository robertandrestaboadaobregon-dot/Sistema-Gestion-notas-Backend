package com.andres.Sistema_Gestion_Notas.service.impl;

import com.andres.Sistema_Gestion_Notas.dto.request.AsistenciaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AsistenciaResponse;
import com.andres.Sistema_Gestion_Notas.entity.Asistencia;
import com.andres.Sistema_Gestion_Notas.entity.Matricula;
import com.andres.Sistema_Gestion_Notas.exception.RecursoNoEncontradoException;
import com.andres.Sistema_Gestion_Notas.exception.ReglaNegocioException;
import com.andres.Sistema_Gestion_Notas.repository.AsistenciaRepository;
import com.andres.Sistema_Gestion_Notas.repository.MatriculaRepository;
import com.andres.Sistema_Gestion_Notas.service.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AsistenciaServiceImpl implements AsistenciaService {


    private final AsistenciaRepository asistenciaRepository;
    private final MatriculaRepository matriculaRepository;



    @Override
    @Transactional(readOnly = true)
    public List<AsistenciaResponse> listarPorMatricula(Long matriculaId) {
        return asistenciaRepository.findByMatriculaId(matriculaId)
                .stream().map(this::toResponse).toList();

    }

    @Override
    public AsistenciaResponse crear(AsistenciaRequest request) {

        Matricula matricula =
                matriculaRepository.findById(request.matriculaId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException
                                ("Matricula no encontrada con id "
                                        + request.matriculaId()));

        // validamos si la fecha coincide que son las mismas
        if(asistenciaRepository.
                findByMatriculaIdAndFecha(request.matriculaId(),
                        request.fecha()).isPresent()){
            throw new
                    ReglaNegocioException(
                            "Ya existe un registro de asistencia " +
                                    "con la misma fecha");
        }

        Asistencia asistencia = Asistencia.builder()
                .matricula(matricula)
                .fecha(request.fecha())
                .estado(request.estado())
                .observacion(request.observacion())
                .build();



        return toResponse(asistenciaRepository.save(asistencia));
    }

    @Override
    public AsistenciaResponse actualizar(Long id, AsistenciaRequest request) {
        Asistencia asistencia = buscarEntidad(id);
        asistencia.setFecha(request.fecha());
        asistencia.setEstado(request.estado());
        asistencia.setObservacion(request.observacion());
        return toResponse(asistenciaRepository.save(asistencia));
    }

    @Override
    public void eliminar(Long id) {
        Asistencia asistencia = buscarEntidad(id);
        asistenciaRepository.delete(asistencia);
    }


    private Asistencia buscarEntidad(Long id){
        return asistenciaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Aistencia no encontrada con el id " + id));
    }

    private AsistenciaResponse toResponse(Asistencia asistencia){
        return new AsistenciaResponse(
                asistencia.getId(),
                asistencia.getMatricula().getId(),
                asistencia.getFecha(),
                asistencia.getEstado().name(),
                asistencia.getObservacion()
        );
    }
}
