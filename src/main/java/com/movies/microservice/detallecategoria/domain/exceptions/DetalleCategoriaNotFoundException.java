package com.movies.microservice.detallecategoria.domain.exceptions;

public class DetalleCategoriaNotFoundException extends RuntimeException {

    public DetalleCategoriaNotFoundException(String message) {
        super(message);
    }
}
