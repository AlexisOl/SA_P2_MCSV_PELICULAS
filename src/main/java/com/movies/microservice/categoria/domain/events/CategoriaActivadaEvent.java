package com.movies.microservice.categoria.domain.events;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CategoriaActivadaEvent {

    private final UUID categoriaId;
    private final Instant occurredAt = Instant.now();

    public CategoriaActivadaEvent(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }
}
