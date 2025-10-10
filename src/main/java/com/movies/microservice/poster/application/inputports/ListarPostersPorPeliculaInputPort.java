
package com.movies.microservice.poster.application.inputports;

import com.movies.microservice.poster.domain.entities.Poster;
import java.util.List;
import java.util.UUID;

public interface ListarPostersPorPeliculaInputPort {

    List<Poster> listar(UUID peliculaId, Boolean activosSolo);
}
