package com.movies.microservice.poster.application.usecases;

import com.movies.microservice.poster.application.inputports.ListarPostersPorPeliculaInputPort;
import com.movies.microservice.poster.application.outputports.persistence.PosterRepositorioOutputPort;
import com.movies.microservice.poster.domain.entities.Poster;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ListarPostersPorPeliculaUseCase implements ListarPostersPorPeliculaInputPort {

    private final PosterRepositorioOutputPort repo;

    @Override
    public List<Poster> listar(UUID peliculaId, Boolean activosSolo) {
        return repo.findByPelicula(peliculaId, activosSolo);
    }
}
