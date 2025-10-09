package com.movies.microservice.categoria.domain.events;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CategoriaActualizadaEvent {

    private final UUID categoriaId;
    private final Instant occurredAt = Instant.now();

    public CategoriaActualizadaEvent(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }
}
