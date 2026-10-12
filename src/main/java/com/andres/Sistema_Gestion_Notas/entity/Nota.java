package com.andres.Sistema_Gestion_Notas.entity;

import com.andres.Sistema_Gestion_Notas.enums.TipoEvaluacion;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id", nullable = false)
    private Matricula matricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evaluacion", nullable = false, length = 20)
    private TipoEvaluacion tipoEvaluacion;
    // 2082,22234
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, precision = 4, scale = 2)
    @Builder.Default
    private BigDecimal peso = BigDecimal.ONE;

    @Column(nullable = false)
    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Column(length = 20)
    private String observacion;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();
    }

}
