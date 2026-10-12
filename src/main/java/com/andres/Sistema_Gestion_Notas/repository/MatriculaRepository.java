package com.andres.Sistema_Gestion_Notas.repository;

import com.andres.Sistema_Gestion_Notas.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {
    List<Matricula> findByAlumnoId(Long alumnoId);
    List<Matricula> findByCursoId(Long cursoId);
    boolean existsByAlumnoIdAndCursoId(Long alumnoId,Long cursoId);
}
