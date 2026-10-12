package com.andres.Sistema_Gestion_Notas.service.impl;

import com.andres.Sistema_Gestion_Notas.dto.request.CursoRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.CursoResponse;
import com.andres.Sistema_Gestion_Notas.dto.response.ProfesorResponse;
import com.andres.Sistema_Gestion_Notas.entity.Curso;
import com.andres.Sistema_Gestion_Notas.entity.Profesor;
import com.andres.Sistema_Gestion_Notas.exception.RecursoNoEncontradoException;
import com.andres.Sistema_Gestion_Notas.exception.ReglaNegocioException;
import com.andres.Sistema_Gestion_Notas.repository.CursoRepository;
import com.andres.Sistema_Gestion_Notas.repository.ProfesorRepository;
import com.andres.Sistema_Gestion_Notas.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar() {
        return cursoRepository.findAll()
                        .stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtenerPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public CursoResponse crear(CursoRequest request) {

        //validamos si existe el codigo
        if(cursoRepository.existsByCode(request.codigo())){
            throw new
                    ReglaNegocioException("Ya existe un curso con el codigo "
                    + request.codigo());
        }

        //buscamos el profesor por id
        Profesor profesor = profesorRepository
                .findById(request.profesorId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Profesor no encontrado con id "
                        + request.profesorId()));

        Curso curso = Curso.builder()
                .code(request.codigo())
                .nombre(request.nombre())
                .creditos(request.creditos())
                .profesor(profesor)
                .build();

        return toResponse(cursoRepository.save(curso));
    }

    @Override
    public CursoResponse actualizar(Long id, CursoRequest request) {
        Curso curso = buscarEntidad(id);

        Profesor profesor = profesorRepository
                .findById(request.profesorId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Profesor no encontrado con id "
                                + request.profesorId()));


        curso.setNombre(request.nombre());
        curso.setCreditos(request.creditos());
        curso.setProfesor(profesor);
        return toResponse(cursoRepository.save(curso));

    }

    @Override
    public void eliminar(Long id) {

        Curso curso = buscarEntidad(id);
        cursoRepository.delete(curso);

    }

    private Curso buscarEntidad(Long id){
        return cursoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException
                                ("Curso no encontrada con el id " + id));
    }

    private CursoResponse toResponse(Curso curso){
        Profesor profesor = curso.getProfesor();
        ProfesorResponse profesorResponse = new ProfesorResponse(
                profesor.getId(),
                profesor.getCode(),
                profesor.getNombres(),
                profesor.getApellidos(),
                profesor.getEmail(),
                profesor.getEspecialidad(),
                profesor.getEstado().name()
        );

        return new CursoResponse(
                curso.getId(),
                curso.getCode(),
                curso.getNombre(),
                curso.getCreditos(),
                profesorResponse,
                curso.getEstado().name()
        );
    }


}
