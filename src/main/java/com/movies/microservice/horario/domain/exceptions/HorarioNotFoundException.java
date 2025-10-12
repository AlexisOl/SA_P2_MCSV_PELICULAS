package com.movies.microservice.horario.domain.exceptions;

public class HorarioNotFoundException extends RuntimeException {
    public HorarioNotFoundException(String message) { super(message); }
}