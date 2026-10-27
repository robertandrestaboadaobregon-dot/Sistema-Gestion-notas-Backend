package com.andres.Sistema_Gestion_Notas.entity;

import com.andres.Sistema_Gestion_Notas.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "profesor")
@SQLDelete(sql = "UPDATE profesor SET eliminado = true WHERE id = ?")
@SQLRestriction("eliminado = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesor extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 20)
    private String code;


    @Column(nullable = false,length = 80)
    private String nombres;

    @Column(nullable = false,length = 80)
    private String apellidos;

    @Column(nullable = false,unique = true,length = 120)
    private String email;

    @Column(length = 80)
    private String especialidad;


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
