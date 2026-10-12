package com.andres.Sistema_Gestion_Notas.service.impl;


import com.andres.Sistema_Gestion_Notas.dto.request.MatriculaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.*;
import com.andres.Sistema_Gestion_Notas.entity.*;
import com.andres.Sistema_Gestion_Notas.enums.EstadoAsistencia;
import com.andres.Sistema_Gestion_Notas.exception.RecursoNoEncontradoException;
import com.andres.Sistema_Gestion_Notas.exception.ReglaNegocioException;
import com.andres.Sistema_Gestion_Notas.repository.*;
import com.andres.Sistema_Gestion_Notas.service.MatriculaService;
import com.andres.Sistema_Gestion_Notas.strategy.PromedioStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final NotaRepository notaRepository;
    private final AsistenciaRepository asistenciaRepository;

    private final PromedioStrategy promedioSimple;
    private final PromedioStrategy promedioPonderado;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository,
                                AlumnoRepository alumnoRepository,
                                CursoRepository cursoRepository,
                                NotaRepository notaRepository,
                                AsistenciaRepository asistenciaRepository,
                                @Qualifier("promedioSimple") PromedioStrategy promedioSimple,
                                @Qualifier("promedioPonderado") PromedioStrategy promedioPonderado) {
        this.matriculaRepository = matriculaRepository;
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.notaRepository = notaRepository;
        this.asistenciaRepository = asistenciaRepository;
        this.promedioSimple = promedioSimple;
        this.promedioPonderado = promedioPonderado;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatriculaResponse> listar() {
        return matriculaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MatriculaResponse obtenerPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public MatriculaResponse crear(MatriculaRequest request) {
        //validamos
        if(matriculaRepository.existsByAlumnoIdAndCursoId(request.alumnoId(),request.cursoId())){
            throw new ReglaNegocioException("El alumno ya esta matriculado en este curso");
        }

        // buscamos el id alumno y el id curso
        // if(alumnoId == true){
        //      alumno = 5
        // }else{
        //      system.print.out("no se enocntro alumno con id " + id)
        // }

        Alumno alumno = alumnoRepository.findById(request.alumnoId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Alumno no encontrado con id "
                                + request.alumnoId()));
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(()-> new RecursoNoEncontradoException("Curso no encontrado con id" +
                        request.cursoId()));

        Matricula matricula = Matricula.builder()
                .alumno(alumno)
                .curso(curso)
                .build();

        return toResponse(matriculaRepository.save(matricula));
    }

    @Override
    public void eliminar(Long id) {
        Matricula matricula = buscarEntidad(id);
        matriculaRepository.delete(matricula);
    }

    @Override
    public List<MatriculaResponse> listarPorAlumno(Long alumnoId) {
        return matriculaRepository.findByAlumnoId(alumnoId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public List<MatriculaResponse> listarPorCurso(Long cursoId) {
        return matriculaRepository.findByCursoId(cursoId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public ResumenAcademicoResponse obtenerResumen(Long matriculaId) {

        // buscamos el id de la matricula
        Matricula matricula = buscarEntidad(matriculaId);
        // buscamos notas
        List<Nota> notas = notaRepository.findByMatriculaId(matriculaId);
        // Buscamos asistencias
        List<Asistencia> asistencias = asistenciaRepository.findByMatriculaId(matriculaId);

        // calcular promedio simple
        BigDecimal pSimple = promedioSimple.calcular(notas);
        // calculamos el promedio ponderado
        BigDecimal pPonderado = promedioPonderado.calcular(notas);

        // Conteo de todas las asistencias
        long total = asistencias.size();
        //conteo de presente + tardanza
        long asistidas = asistencias.stream()
                .filter( asistio ->
                        asistio.getEstado() == EstadoAsistencia.PRESENTE ||
                                asistio.getEstado() == EstadoAsistencia.TARDANZA)
                .count();

        // calcular las asistencias validas y asistencias totales

        BigDecimal porcentaje = total == 0
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(asistidas)
                .divide(BigDecimal.valueOf(total),4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2,RoundingMode.HALF_UP);

        /**
        *   asistencias validas
        *   _____________________ x 100
         *
         *  asistencias totales
        **/

        ///obtener el porcentaje

        String nombrealumno = matricula.getAlumno().getNombres() + " " +
                matricula.getAlumno().getApellidos();


        return new ResumenAcademicoResponse(
                matriculaId,
                nombrealumno,
                matricula.getCurso().getNombre(),
                pSimple,
                pPonderado,
                total,
                asistidas,
                porcentaje
                );
    }

    private Matricula buscarEntidad(Long id){

        return matriculaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Matricula no encontrada con id " + id));

    }

    private MatriculaResponse toResponse(Matricula matricula){
        Alumno alumno = matricula.getAlumno();
        Curso curso = matricula.getCurso();
        Profesor profesor = curso.getProfesor();

        AlumnoResponse alumnoResponse = new AlumnoResponse(
                alumno.getId(),
                alumno.getCode(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getEmail(),
                alumno.getFechaNacimiento(),
                alumno.getEstado().name()
        );

        ProfesorResponse profesorResponse = new ProfesorResponse(
                profesor.getId(),
                profesor.getCode(),
                profesor.getNombres(),
                profesor.getApellidos(),
                profesor.getEmail(),
                profesor.getEspecialidad(),
                profesor.getEstado().name()

        );

        CursoResponse cursoResponse = new CursoResponse(
                curso.getId(),
                curso.getCode(),
                curso.getNombre(),
                curso.getCreditos(),
                profesorResponse,
                curso.getEstado().name()
        );

        return new MatriculaResponse(
                matricula.getId(),
                alumnoResponse,
                cursoResponse,
                matricula.getFechaMatricula(),
                matricula.getEstado().name()
                );


    }



}
