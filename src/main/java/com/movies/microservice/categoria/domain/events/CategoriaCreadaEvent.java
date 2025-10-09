package com.movies.microservice.categoria.domain.events;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CategoriaCreadaEvent {

    private final UUID categoriaId;
    private final Instant occurredAt = Instant.now();

    public CategoriaCreadaEvent(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }
}
