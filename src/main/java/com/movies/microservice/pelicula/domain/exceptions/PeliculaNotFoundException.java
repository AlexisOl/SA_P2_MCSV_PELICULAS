package com.movies.microservice.pelicula.domain.exceptions;

public class PeliculaNotFoundException extends RuntimeException {

    public PeliculaNotFoundException(String message) {
        super(message);
    }
}
