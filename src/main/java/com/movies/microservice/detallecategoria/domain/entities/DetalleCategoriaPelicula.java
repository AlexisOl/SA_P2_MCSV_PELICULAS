package com.movies.microservice.detallecategoria.domain.entities;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCategoriaPelicula {

    private UUID id;
    private UUID peliculaId;
    private UUID categoriaId;
    private LocalDateTime createdAt;

    public DetalleCategoriaPelicula(UUID peliculaId, UUID categoriaId) {
        if (peliculaId == null || categoriaId == null) {
            throw new IllegalArgumentException("La película y la categoría son obligatorias");
        }
        this.id = UUID.randomUUID();
        this.peliculaId = peliculaId;
        this.categoriaId = categoriaId;
        this.createdAt = LocalDateTime.now();
    }
}
