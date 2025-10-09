package com.movies.microservice.categoria.domain.exceptions;

public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(String message) {
        super(message);
    }
}
