package com.andres.Sistema_Gestion_Notas.repository;

import com.andres.Sistema_Gestion_Notas.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno,Long> {
    Optional<Alumno> findByDni(String dni);
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    boolean existsByCode(String codigo);
    boolean existsByDniAndIdNot(String dni,Long id);
    boolean existsByEmailAndIdNot(String email,Long id);

}
