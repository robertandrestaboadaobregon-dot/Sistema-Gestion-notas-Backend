package com.andres.Sistema_Gestion_Notas.service.impl;

import com.andres.Sistema_Gestion_Notas.dto.request.NotaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AlumnoResponse;
import com.andres.Sistema_Gestion_Notas.dto.response.NotaResponse;
import com.andres.Sistema_Gestion_Notas.entity.Alumno;
import com.andres.Sistema_Gestion_Notas.entity.Matricula;
import com.andres.Sistema_Gestion_Notas.entity.Nota;
import com.andres.Sistema_Gestion_Notas.exception.RecursoNoEncontradoException;
import com.andres.Sistema_Gestion_Notas.repository.MatriculaRepository;
import com.andres.Sistema_Gestion_Notas.repository.NotaRepository;
import com.andres.Sistema_Gestion_Notas.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final MatriculaRepository matriculaRepository;


    @Override
    public List<NotaResponse> listarPorMatricula(Long matriculaId) {
        return notaRepository.findByMatriculaId(matriculaId).stream().map(this::toResponse).toList();
    }

    @Override
    public NotaResponse crear(NotaRequest request) {

        Matricula matricula = matriculaRepository.findById(request.matriculaId())
                .orElseThrow(()->
                        new RecursoNoEncontradoException("Matricula no encotrada con Id " + request.matriculaId()));

        Nota nota = Nota.builder()
                .matricula(matricula)
                .tipoEvaluacion(request.tipoEvaluacion())
                .valor(request.valor())
                .peso(request.peso() != null ? request.peso() : BigDecimal.ZERO)
                .fecha(request.fecha() != null ? request.fecha() : LocalDate.now())
                .observacion(request.observacion())
                .build();
        return toResponse(notaRepository.save(nota));
    }




    @Override
    public NotaResponse actualizar(Long id, NotaRequest request) {
        Nota nota = buscarEntidad(id);
        nota.setTipoEvaluacion(request.tipoEvaluacion());
        nota.setValor(request.valor());
        //validamos el peso y la fecha
        if(request.peso() != null) nota.setPeso(request.peso());
        if (request.fecha() != null) nota.setFecha(request.fecha());
        nota.setObservacion(request.observacion());
        return toResponse(notaRepository.save(nota));

    }

    @Override
    public void eliminar(Long id) {
        Nota nota = buscarEntidad(id);
        notaRepository.delete(nota);

    }

    public NotaResponse toResponse(Nota nota){
        return new NotaResponse(
                nota.getId(),
                nota.getMatricula().getId(),
                nota.getTipoEvaluacion().name(),
                nota.getValor(),
                nota.getPeso(),
                nota.getFecha(),
                nota.getObservacion()
        );
    }

    private Nota buscarEntidad(Long id){
        return notaRepository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("Nota no encontrado con el id " + id));
    }




}
