package com.andres.Sistema_Gestion_Notas.entity;

import com.andres.Sistema_Gestion_Notas.enums.EstadoAsistencia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asistencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id", nullable = false)
    private Matricula matricula;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 15)
    private EstadoAsistencia estado;

    @Column(length = 200)
    private String observacion;

    @Column(name="creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();
    }





}
