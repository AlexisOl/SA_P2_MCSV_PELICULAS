package com.movies.microservice.pelicula.application.usecases;

import com.movies.microservice.pelicula.application.inputports.ActivarPeliculaInputPort;
import com.movies.microservice.pelicula.application.outputports.messaging.PeliculaEventPublisherOutputPort;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.domain.events.PeliculaActivadaEvent;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ActivarPeliculaUseCase implements ActivarPeliculaInputPort {

    private final @NonNull
    PeliculaRepositorioOutputPort repo;
    private final @NonNull
    PeliculaEventPublisherOutputPort publisher;

    @Override
    public Pelicula activar(UUID peliculaId) {
        Pelicula pelicula = repo.findById(new PeliculaId(peliculaId))
                .orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada"));
        if (!pelicula.isActiva()) {
            pelicula.activar();
            Pelicula updated = repo.update(pelicula);
            publisher.publish(new PeliculaActivadaEvent(updated.getId()));
            return updated;
        }
        return pelicula;
    }
}
