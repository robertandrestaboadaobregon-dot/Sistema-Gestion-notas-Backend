package com.andres.Sistema_Gestion_Notas.service.impl;

import com.andres.Sistema_Gestion_Notas.dto.request.ProfesorRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AlumnoResponse;
import com.andres.Sistema_Gestion_Notas.dto.response.ProfesorResponse;
import com.andres.Sistema_Gestion_Notas.entity.Alumno;
import com.andres.Sistema_Gestion_Notas.entity.Profesor;
import com.andres.Sistema_Gestion_Notas.exception.RecursoNoEncontradoException;
import com.andres.Sistema_Gestion_Notas.exception.ReglaNegocioException;
import com.andres.Sistema_Gestion_Notas.repository.ProfesorRepository;
import com.andres.Sistema_Gestion_Notas.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository repository;


    @Override
    public List<ProfesorResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ProfesorResponse obtenerPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public ProfesorResponse crear(ProfesorRequest request) {
        if (repository.existsByCode(request.codigo())) {
            throw new ReglaNegocioException("Ya existe un profesor con el codigo " + request.codigo());
        }

        if (repository.existsByEmail(request.email())) {
            throw new ReglaNegocioException("Ya existe un profesor con el email " + request.email());
        }

        Profesor profesor = Profesor.builder()
                .code(request.codigo())
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .email(request.email())
                .especialidad(request.especialidad())
                .build();
        return toResponse(repository.save(profesor));
    }

    @Override
    public ProfesorResponse actualizar(Long id, ProfesorRequest request) {
        Profesor profesor = buscarEntidad(id);
        profesor.setNombres(request.nombres());
        profesor.setApellidos(request.apellidos());
        profesor.setEmail(request.email());
        profesor.setEspecialidad(request.especialidad());
        return toResponse(repository.save(profesor));
    }

    @Override
    public void eliminar(Long id) {
        Profesor profesor = buscarEntidad(id);
        repository.delete(profesor);
    }

    public ProfesorResponse toResponse(Profesor profesor){
        return new ProfesorResponse(
                profesor.getId(),
                profesor.getCode(),
                profesor.getNombres(),
                profesor.getApellidos(),
                profesor.getEmail(),
                profesor.getEspecialidad(),
                profesor.getEstado().name());
    }

    private Profesor buscarEntidad(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("Profesor no encontrado con el id " + id));
    }
}
