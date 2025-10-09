package com.movies.microservice.categoria.infrastructure.inputadapters.rest.mapper;

import com.movies.microservice.categoria.domain.entities.Categoria;
import com.movies.microservice.categoria.infrastructure.inputadapters.rest.dto.CategoriaRequest;
import com.movies.microservice.categoria.infrastructure.inputadapters.rest.dto.CategoriaResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public class CategoriaRestMapper {

    public static Categoria toDomain(CategoriaRequest req) {
        return Categoria.builder()
                .id(UUID.randomUUID())
                .nombre(req.getNombre())
                .activa(req.getActiva() != null ? req.getActiva() : true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CategoriaResponse toResponse(Categoria c) {
        return CategoriaResponse.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .activa(c.isActiva())
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }
}
