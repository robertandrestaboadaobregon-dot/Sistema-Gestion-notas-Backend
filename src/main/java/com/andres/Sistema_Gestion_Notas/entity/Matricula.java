package com.andres.Sistema_Gestion_Notas.entity;

import com.andres.Sistema_Gestion_Notas.enums.EstadoMatricula;
import com.andres.Sistema_Gestion_Notas.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "matricula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "fecha_matricula",nullable = false)
    @Builder.Default
    private LocalDate fechaMatricula = LocalDate.now();

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 15)
    @Builder.Default
    private EstadoMatricula estado = EstadoMatricula.ACTIVA;

    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();
    }
}
