
package com.movies.microservice.poster.application.outputports.persistence;

import com.movies.microservice.poster.domain.entities.Poster;
import java.util.*;

public interface PosterRepositorioOutputPort {

    Poster save(Poster poster);

    Optional<Poster> findById(UUID posterId);

    List<Poster> findByPelicula(UUID peliculaId, Boolean activosSolo);

    boolean existsByPeliculaAndUrl(UUID peliculaId, String url);

    void delete(UUID posterId);
}
