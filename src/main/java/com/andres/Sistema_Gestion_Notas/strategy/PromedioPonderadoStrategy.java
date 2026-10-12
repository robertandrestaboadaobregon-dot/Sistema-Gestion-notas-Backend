package com.andres.Sistema_Gestion_Notas.strategy;

import com.andres.Sistema_Gestion_Notas.entity.Nota;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Promedio ponderado: cada nota se multiplica por su peso
 * promedio = sum(valor * peso) / sum(peso)
 * ***/
@Component("promedioPonderado")
public class PromedioPonderadoStrategy implements PromedioStrategy{

    @Override
    public BigDecimal calcular(List<Nota> notas) {
        if(notas == null || notas.isEmpty()){
            return  BigDecimal.ZERO;
        }

        BigDecimal sumaPonderada = BigDecimal.ZERO;
        BigDecimal sumaPesos = BigDecimal.ZERO;
        // [12,16,18,20,15,14]
        for(Nota nota : notas){
            sumaPonderada = sumaPonderada.add(nota.getValor().multiply(nota.getPeso()));
            sumaPesos = sumaPesos.add(nota.getPeso());
        }

        if(sumaPesos.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }

        return sumaPonderada.divide(sumaPesos,2, RoundingMode.HALF_UP);
    }
}
