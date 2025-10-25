
package com.movies.microservice.poster.application.usecases;

import com.movies.microservice.poster.application.inputports.EliminarPosterInputPort;
import com.movies.microservice.poster.application.outputports.persistence.PosterRepositorioOutputPort;
import com.movies.microservice.poster.application.outputports.storage.PosterStorageOutputPort;
import com.movies.microservice.poster.domain.entities.Poster;
import com.movies.microservice.poster.domain.exceptions.PosterNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class EliminarPosterUseCase implements EliminarPosterInputPort {

    private final PosterRepositorioOutputPort repo;
    private final PosterStorageOutputPort storage;

    @Override
    public void eliminar(UUID posterId) {
        Poster poster = repo.findById(posterId)
                .orElseThrow(() -> new PosterNotFoundException("Poster no encontrado"));
        storage.delete(poster.getUrl());
        repo.delete(posterId);
    }
}
