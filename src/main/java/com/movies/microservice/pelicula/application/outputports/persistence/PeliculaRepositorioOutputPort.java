package com.movies.microservice.pelicula.application.outputports.persistence;

import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PeliculaRepositorioOutputPort {

    Pelicula save(Pelicula pelicula);

    Pelicula update(Pelicula pelicula);

    Optional<Pelicula> findById(PeliculaId id);

    boolean existsByTituloAndReleaseDate(String titulo, LocalDate releaseDate);

    List<Pelicula> search(String texto,
            String clasificacion,
            Boolean activa,
            LocalDate releasedFrom,
            LocalDate releasedTo,
            List<String> categorias, // si lo resolvemos aquí o en hexágono Categoría
            int page, int size, String sort);
}
