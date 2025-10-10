
package com.movies.microservice.poster.application.usecases;

import com.movies.microservice.poster.application.inputports.ActivarPosterInputPort;
import com.movies.microservice.poster.application.outputports.messaging.PosterEventPublisherOutputPort;
import com.movies.microservice.poster.application.outputports.persistence.PosterRepositorioOutputPort;
import com.movies.microservice.poster.domain.entities.Poster;
import com.movies.microservice.poster.domain.events.PosterActivadoEvent;
import com.movies.microservice.poster.domain.exceptions.PosterNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class ActivarPosterUseCase implements ActivarPosterInputPort {

    private final PosterRepositorioOutputPort repo;
    private final PosterEventPublisherOutputPort publisher;

    @Override
    public Poster activar(UUID posterId) {
        Poster poster = repo.findById(posterId)
                .orElseThrow(() -> new PosterNotFoundException("Poster no encontrado"));
        if (!poster.isActivo()) {
            poster.activar();
            poster.setUpdatedAt(LocalDateTime.now());
            poster = repo.save(poster);
            publisher.publish(new PosterActivadoEvent(poster.getId(), poster.getPeliculaId()));
        }
        return poster;
    }
}
