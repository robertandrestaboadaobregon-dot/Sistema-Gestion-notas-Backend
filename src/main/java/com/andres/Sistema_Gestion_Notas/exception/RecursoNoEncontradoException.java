package com.andres.Sistema_Gestion_Notas.exception;

public class RecursoNoEncontradoException extends RuntimeException{

    // constructor con parametros
    public RecursoNoEncontradoException(String mensaje){
        /// el super sirve para heredar todos los atributos de su clase padre
        super(mensaje);
    }
}
