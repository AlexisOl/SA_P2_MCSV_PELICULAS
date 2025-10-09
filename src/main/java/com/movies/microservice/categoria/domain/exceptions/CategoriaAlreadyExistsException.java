package com.movies.microservice.categoria.domain.exceptions;

public class CategoriaAlreadyExistsException extends RuntimeException {

    public CategoriaAlreadyExistsException(String nombre) {
        super("Ya existe una categoría con el nombre: " + nombre);
    }
}
