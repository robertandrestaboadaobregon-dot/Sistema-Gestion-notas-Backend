package com.andres.Sistema_Gestion_Notas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Auditable {

    @Column(nullable = false)
    private boolean eliminado = false;

}
