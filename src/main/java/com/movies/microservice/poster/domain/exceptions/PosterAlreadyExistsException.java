package com.movies.microservice.poster.domain.exceptions;

public class PosterAlreadyExistsException extends RuntimeException {

    public PosterAlreadyExistsException(String msg) {
        super(msg);
    }
}
