package com.andres.Sistema_Gestion_Notas.controller;

import com.andres.Sistema_Gestion_Notas.dto.request.AlumnoRequest;
import com.andres.Sistema_Gestion_Notas.dto.request.AsistenciaRequest;
import com.andres.Sistema_Gestion_Notas.dto.response.AlumnoResponse;
import com.andres.Sistema_Gestion_Notas.dto.response.AsistenciaResponse;
import com.andres.Sistema_Gestion_Notas.service.AsistenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping("/matricula/{matriculaId}")
    public List<AsistenciaResponse> listar(@PathVariable Long matriculaId){
        return asistenciaService.listarPorMatricula(matriculaId);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AsistenciaResponse crear(@Valid @RequestBody AsistenciaRequest request ){
        return  asistenciaService.crear(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AsistenciaResponse actualizar(@PathVariable Long id,
                                     @Valid @RequestBody AsistenciaRequest request){
        return  asistenciaService.actualizar(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        asistenciaService.eliminar(id);
    }
}
