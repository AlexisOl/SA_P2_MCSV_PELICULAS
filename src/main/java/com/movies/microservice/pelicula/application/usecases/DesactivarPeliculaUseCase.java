package com.movies.microservice.pelicula.application.usecases;

import com.movies.microservice.pelicula.application.inputports.DesactivarPeliculaInputPort;
import com.movies.microservice.pelicula.application.outputports.messaging.PeliculaEventPublisherOutputPort;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.domain.events.PeliculaDesactivadaEvent;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class DesactivarPeliculaUseCase implements DesactivarPeliculaInputPort {

    private final @NonNull
    PeliculaRepositorioOutputPort repo;
    private final @NonNull
    PeliculaEventPublisherOutputPort publisher;

    @Override
    @Transactional
    public Pelicula desactivar(UUID peliculaId) {
        Pelicula pelicula = repo.findById(new PeliculaId(peliculaId))
                .orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada"));
        if (pelicula.isActiva()) {
            pelicula.desactivar();
            Pelicula updated = repo.update(pelicula);
            publisher.publish(new PeliculaDesactivadaEvent(updated.getId()));
            return updated;
        }
        return pelicula;
    }
}
