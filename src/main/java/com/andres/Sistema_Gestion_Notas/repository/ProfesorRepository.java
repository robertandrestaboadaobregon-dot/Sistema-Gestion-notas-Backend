package com.andres.Sistema_Gestion_Notas.repository;

import com.andres.Sistema_Gestion_Notas.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {

    Optional<Profesor> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCode(String codigo);

}
