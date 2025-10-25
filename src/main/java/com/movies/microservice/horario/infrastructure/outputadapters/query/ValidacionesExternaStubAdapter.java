package com.movies.microservice.horario.infrastructure.outputadapters.query;

import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.movies.microservice.horario.application.outputports.query.ValidacionesExternaOutputPort;

@Component
@ConditionalOnProperty(prefix = "horario.validation", name = "external", havingValue = "stub", matchIfMissing = true)
public class ValidacionesExternaStubAdapter implements ValidacionesExternaOutputPort {

    @Override public boolean existePelicula(UUID peliculaId) { return true; }
    @Override public boolean existeCinema(UUID cinemaId) { return true; }
    @Override public boolean existeSala(UUID salaId) { return true; }
}