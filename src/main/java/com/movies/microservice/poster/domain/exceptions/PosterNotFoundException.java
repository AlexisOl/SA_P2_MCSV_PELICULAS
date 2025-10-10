package com.movies.microservice.poster.domain.exceptions;

public class PosterNotFoundException extends RuntimeException {

    public PosterNotFoundException(String msg) {
        super(msg);
    }
}
