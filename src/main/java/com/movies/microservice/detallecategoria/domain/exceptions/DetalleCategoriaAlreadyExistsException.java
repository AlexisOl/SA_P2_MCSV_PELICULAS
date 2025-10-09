package com.movies.microservice.detallecategoria.domain.exceptions;

public class DetalleCategoriaAlreadyExistsException extends RuntimeException {

    public DetalleCategoriaAlreadyExistsException(String message) {
        super(message);
    }
}
