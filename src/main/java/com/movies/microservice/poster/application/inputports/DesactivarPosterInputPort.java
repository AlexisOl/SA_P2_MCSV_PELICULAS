
package com.movies.microservice.poster.application.inputports;

import com.movies.microservice.poster.domain.entities.Poster;
import java.util.UUID;

public interface DesactivarPosterInputPort {

    Poster desactivar(UUID posterId);
}
