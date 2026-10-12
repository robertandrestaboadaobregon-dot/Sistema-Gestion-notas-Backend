package com.andres.Sistema_Gestion_Notas.entity;

import com.andres.Sistema_Gestion_Notas.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "alumno")
@Getter
@Setter
@NoArgsConstructor // constructor sin parametros
@AllArgsConstructor// constructor con parametros
@Builder// evitar los setter en otras clases
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String code;


    @Column(nullable = false, length = 80)
    private String nombres;

    @Column(nullable = false, length = 80)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Builder.Default
    private EstadoRegistro estado = EstadoRegistro.ACTIVO;

    // updatable = una vez creado no se va a actualizar
    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    //es una anotacion que se va a ejecutar antes de insertar una nueva entidad
    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();
    }






}
