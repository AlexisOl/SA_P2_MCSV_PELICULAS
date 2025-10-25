package com.movies.microservice.detallecategoria.application.outputports.query;

import java.util.UUID;

public interface PeliculaExistenciaOutputPort {

    boolean existsById(UUID peliculaId);  // valida que la película exista
}
