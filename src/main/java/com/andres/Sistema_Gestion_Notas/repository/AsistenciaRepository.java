package com.andres.Sistema_Gestion_Notas.repository;

import com.andres.Sistema_Gestion_Notas.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Long> {
    List<Asistencia> findByMatriculaId(Long matriculaId);
    Optional<Asistencia> findByMatriculaIdAndFecha(Long matriculaId, LocalDate fecha);

}
