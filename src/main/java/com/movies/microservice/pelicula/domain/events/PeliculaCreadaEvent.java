package com.movies.microservice.pelicula.domain.events;

import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;
import java.time.Instant;

public class PeliculaCreadaEvent {

    private final PeliculaId peliculaId;
    private final Instant occurredAt = Instant.now();

    public PeliculaCreadaEvent(PeliculaId peliculaId) {
        this.peliculaId = peliculaId;
    }

    public PeliculaId getPeliculaId() {
        return peliculaId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
