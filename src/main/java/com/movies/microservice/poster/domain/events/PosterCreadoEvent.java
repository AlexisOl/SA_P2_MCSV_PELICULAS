package com.movies.microservice.poster.domain.events;

import lombok.Getter;
import java.time.Instant;
import java.util.UUID;

@Getter
public class PosterCreadoEvent {
    private final UUID posterId;
    private final UUID peliculaId;
    private final Instant occurredAt = Instant.now();

    public PosterCreadoEvent(UUID posterId, UUID peliculaId) {
        this.posterId = posterId;
        this.peliculaId = peliculaId;
    }
}