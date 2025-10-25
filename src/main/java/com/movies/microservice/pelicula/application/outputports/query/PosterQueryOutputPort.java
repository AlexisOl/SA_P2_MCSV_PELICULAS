package com.movies.microservice.pelicula.application.outputports.query;

import java.util.List;
import java.util.UUID;

public interface PosterQueryOutputPort {
    List<String> findUrlsByPelicula(UUID peliculaId);  // solo activos, ordenados
}