package com.andres.Sistema_Gestion_Notas.strategy;

import com.andres.Sistema_Gestion_Notas.entity.Nota;

import java.math.BigDecimal;
import java.util.List;


/**
* Estrategia de calculo de promedio (patron Strategy)
 * Permite intercambiar la formula de promedio
 * sin modificar el
 * servicio que la usa
*
* */
public interface PromedioStrategy {
    BigDecimal calcular(List<Nota> notas);
}
