package com.andres.Sistema_Gestion_Notas.entity;

import com.andres.Sistema_Gestion_Notas.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 20)
    private String code;

    @Column(nullable = false,length = 100)
    private String nombre;


    @Column(nullable = false)
    @Builder.Default // se da por defecto un valor
    private Integer creditos = 3;

    // relacion

    // un curso y no va a cargar hasta que lo necesites eso seria lazy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id",nullable = false)
    private Profesor profesor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 15)
    @Builder.Default
    private EstadoRegistro estado = EstadoRegistro.ACTIVO;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();
    }





}
