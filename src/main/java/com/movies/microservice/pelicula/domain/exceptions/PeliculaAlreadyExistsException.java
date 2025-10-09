package com.movies.microservice.pelicula.domain.exceptions;

public class PeliculaAlreadyExistsException extends RuntimeException {

    public PeliculaAlreadyExistsException(String message) {
        super(message);
    }
}
