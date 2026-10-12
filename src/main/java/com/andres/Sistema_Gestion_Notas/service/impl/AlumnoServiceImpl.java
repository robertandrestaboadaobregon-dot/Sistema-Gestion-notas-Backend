package com.andres.Sistema_Gestion_Notas.service.impl;

import com.andres.Sistema_Gestion_Notas.dto.request.AlumnoRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AlumnoResponse;
import com.andres.Sistema_Gestion_Notas.entity.Alumno;
import com.andres.Sistema_Gestion_Notas.exception.RecursoNoEncontradoException;
import com.andres.Sistema_Gestion_Notas.exception.ReglaNegocioException;
import com.andres.Sistema_Gestion_Notas.repository.AlumnoRepository;
import com.andres.Sistema_Gestion_Notas.service.AlumnoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// sirve para que esta clase cuando hacemos inyeccion de dependencia
// lo detecte como una clase especial y asi poder extraer sus metodos
@Service
@RequiredArgsConstructor // esta anotacion es para generar automaticamente un constructor
@Transactional // para poder modificar o no modificar nuestra base de datos
public class AlumnoServiceImpl implements AlumnoService {

    private  final AlumnoRepository repository;


    @Override
    @Transactional(readOnly = true)// no modifica la base de datos
    public List<AlumnoResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponse obtenerPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public AlumnoResponse crear(AlumnoRequest request) {
        // si existe un alumno con el mismo codigo
        if (repository.existsByCode(request.codigo())){
            throw new ReglaNegocioException("Ya existe un alumno con el mismo codigo " + request.codigo());
        }

        if (repository.existsByDni(request.dni())){
            throw new ReglaNegocioException("Ya existe un alumno con el mismo DNI " + request.dni());
        }

        if (repository.existsByEmail(request.email())){
            throw new ReglaNegocioException("Ya existe un alumno con el mismo email " + request.email());
        }

        Alumno alumno = Alumno.builder()
                .code(request.codigo())
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .dni(request.dni())
                .email(request.email())
                .fechaNacimiento(request.fechaNacimiento())
                .build();
        return toResponse(repository.save(alumno));
    }

    @Override
    public AlumnoResponse actualizar(Long id, AlumnoRequest request) {
        // antes de actualizar tenemos que comprobar que exista ese id
        Alumno alumno = buscarEntidad(id);

        if (repository.existsByDniAndIdNot(request.dni(), id)){
            throw new ReglaNegocioException("Ya existe otro alumno con el mismo DNI  " + request.dni());
        }

        if (repository.existsByEmailAndIdNot(request.email(), id)){
            throw new ReglaNegocioException("Ya existe otro alumno con el mismo Email  " + request.email());
        }

        alumno.setNombres(request.nombres());
        alumno.setApellidos(request.apellidos());
        alumno.setEmail(request.email());
        alumno.setFechaNacimiento(request.fechaNacimiento());
        return toResponse(repository.save(alumno));
    }


    @Override
    public void eliminar(Long id) {
        Alumno alumno = buscarEntidad(id);
        repository.delete(alumno);
    }

    public AlumnoResponse toResponse(Alumno alumno){
        return new AlumnoResponse(
                alumno.getId(),
                alumno.getCode(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getEmail(),
                alumno.getFechaNacimiento(),
                alumno.getEstado().name()
        );
    }

    private Alumno buscarEntidad(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("Alumno no encontrado con el id " + id));
    }


}
