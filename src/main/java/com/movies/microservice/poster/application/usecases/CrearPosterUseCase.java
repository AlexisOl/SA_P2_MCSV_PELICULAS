
package com.movies.microservice.poster.application.usecases;

import com.movies.microservice.poster.application.inputports.CrearPosterInputPort;
import com.movies.microservice.poster.application.outputports.messaging.PosterEventPublisherOutputPort;
import com.movies.microservice.poster.application.outputports.persistence.PosterRepositorioOutputPort;
import com.movies.microservice.poster.application.outputports.query.PeliculaExistenciaOutputPort;
import com.movies.microservice.poster.application.outputports.storage.PosterStorageOutputPort;
import com.movies.microservice.poster.domain.entities.Poster;
import com.movies.microservice.poster.domain.events.PosterCreadoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class CrearPosterUseCase implements CrearPosterInputPort {

    private final PosterRepositorioOutputPort repo;
    private final PosterStorageOutputPort storage;
    private final PeliculaExistenciaOutputPort peliculaQuery;
    private final PosterEventPublisherOutputPort publisher;

    @Override
    public Poster crear(UUID peliculaId, MultipartFile archivo, Integer orden) {
        if (peliculaId == null || archivo == null) {
            throw new IllegalArgumentException("Película y archivo son obligatorios");
        }
        if (!peliculaQuery.existsById(peliculaId)) {
            throw new IllegalArgumentException("Película no existe");
        }

        UUID posterId = UUID.randomUUID();
        String url = storage.upload(posterId, archivo);

        // Evitar duplicado por URL (misma película)
        if (repo.existsByPeliculaAndUrl(peliculaId, url)) {
            throw new IllegalArgumentException("Ya existe un poster con esa URL para la película");
        }

        Poster poster = Poster.builder()
                .id(posterId)
                .peliculaId(peliculaId)
                .url(url)
                .activo(true)
                .orden(orden != null ? orden : 0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Poster saved = repo.save(poster);
        publisher.publish(new PosterCreadoEvent(saved.getId(), saved.getPeliculaId()));
        return saved;
    }
}
