
package com.movies.microservice.poster.application.usecases;

import com.movies.microservice.poster.application.inputports.ActualizarPosterInputPort;
import com.movies.microservice.poster.application.outputports.persistence.PosterRepositorioOutputPort;
import com.movies.microservice.poster.application.outputports.storage.PosterStorageOutputPort;
import com.movies.microservice.poster.domain.entities.Poster;
import com.movies.microservice.poster.domain.exceptions.PosterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class ActualizarPosterUseCase implements ActualizarPosterInputPort {

    private final PosterRepositorioOutputPort repo;
    private final PosterStorageOutputPort storage;

    @Override
    public Poster actualizar(UUID posterId, MultipartFile nuevoArchivo) {
        Poster poster = repo.findById(posterId)
                .orElseThrow(() -> new PosterNotFoundException("Poster no encontrado"));

        String newUrl = storage.replace(poster.getId(), nuevoArchivo, poster.getUrl());
        poster.setUrl(newUrl);
        poster.setUpdatedAt(LocalDateTime.now());
        return repo.save(poster);
    }
}
