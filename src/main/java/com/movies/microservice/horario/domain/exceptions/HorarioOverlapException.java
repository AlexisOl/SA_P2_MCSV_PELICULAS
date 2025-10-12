package com.movies.microservice.horario.domain.exceptions;

public class HorarioOverlapException extends RuntimeException {
    public HorarioOverlapException(String message) { super(message); }
}
