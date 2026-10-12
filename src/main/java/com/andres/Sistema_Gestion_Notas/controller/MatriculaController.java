package com.andres.Sistema_Gestion_Notas.controller;

import com.andres.Sistema_Gestion_Notas.dto.request.AlumnoRequest;
import com.andres.Sistema_Gestion_Notas.dto.request.MatriculaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AlumnoResponse;
import com.andres.Sistema_Gestion_Notas.dto.response.MatriculaResponse;
import com.andres.Sistema_Gestion_Notas.dto.response.ResumenAcademicoResponse;
import com.andres.Sistema_Gestion_Notas.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @GetMapping
    public List<MatriculaResponse> listar(){
        return matriculaService.listar();
    }

    @GetMapping("/{id}")
    public MatriculaResponse obtener(@PathVariable Long id){
        return matriculaService.obtenerPorId(id);
    }

    @GetMapping("/alumno/alumnoId")
    public List<MatriculaResponse> listarporAlumno(@PathVariable Long alumnoId){
        return matriculaService.listarPorAlumno(alumnoId);
    }

    @GetMapping("/curso/cursoId")
    public List<MatriculaResponse> listarporCurso(@PathVariable Long cursoId){
        return matriculaService.listarPorCurso(cursoId);
    }

    @GetMapping("/{id}/resumen")
    public ResumenAcademicoResponse obtenerResumen(@PathVariable Long id){
        return matriculaService.obtenerResumen(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse crear(@Valid @RequestBody MatriculaRequest request ){
        return  matriculaService.crear(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        matriculaService.eliminar(id);
    }

}
