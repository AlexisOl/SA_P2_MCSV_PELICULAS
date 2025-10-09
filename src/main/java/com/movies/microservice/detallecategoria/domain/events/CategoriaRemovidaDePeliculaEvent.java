package com.movies.microservice.detallecategoria.domain.events;

import lombok.Getter;
import java.time.Instant;
import java.util.UUID;

@Getter
public class CategoriaRemovidaDePeliculaEvent {

    private final UUID peliculaId;
    private final UUID categoriaId;
    private final Instant occurredAt = Instant.now();

    public CategoriaRemovidaDePeliculaEvent(UUID peliculaId, UUID categoriaId) {
        this.peliculaId = peliculaId;
        this.categoriaId = categoriaId;
    }
}
