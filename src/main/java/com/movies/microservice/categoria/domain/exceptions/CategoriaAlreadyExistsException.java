package com.movies.microservice.categoria.domain.exceptions;


// categoria/domain/exceptions/CategoriaAlreadyExistsException.java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoriaAlreadyExistsException extends RuntimeException {

    public CategoriaAlreadyExistsException(String nombre) {
        super("Ya existe una categoría con el nombre: " + nombre);
    }
}
