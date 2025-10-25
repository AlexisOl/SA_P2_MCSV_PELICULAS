package com.movies.microservice.categoria.domain.exceptions;

<<<<<<< HEAD
=======
<<<<<<< HEAD

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(String msg) {
        super(msg);
=======
>>>>>>> dev
public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(String message) {
        super(message);
<<<<<<< HEAD
=======
>>>>>>> 17fa7f3 (first commit)
>>>>>>> dev
    }
}
