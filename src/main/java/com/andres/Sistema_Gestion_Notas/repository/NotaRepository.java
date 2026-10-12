package com.andres.Sistema_Gestion_Notas.repository;

import com.andres.Sistema_Gestion_Notas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota,Long> {

    List<Nota> findByMatriculaId(Long matriculaId);

}
