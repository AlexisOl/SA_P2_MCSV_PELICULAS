
package com.movies.microservice.poster.application.outputports.query;

import java.util.UUID;

public interface PeliculaExistenciaOutputPort {

    boolean existsById(UUID peliculaId);
}
