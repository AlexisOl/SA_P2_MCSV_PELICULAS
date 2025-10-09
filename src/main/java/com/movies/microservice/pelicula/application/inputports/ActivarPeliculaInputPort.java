package com.movies.microservice.pelicula.application.inputports;

import java.util.UUID;
import com.movies.microservice.pelicula.domain.Pelicula;

public interface ActivarPeliculaInputPort {

    Pelicula activar(UUID peliculaId);
}
