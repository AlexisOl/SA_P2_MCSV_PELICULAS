package com.movies.microservice.horario.application.outputports.query;

import java.util.UUID;

public interface ValidacionesExternaOutputPort {

    boolean existePelicula(UUID peliculaId);

    boolean existeCinema(UUID cinemaId);

    boolean existeSala(UUID salaId);
}
