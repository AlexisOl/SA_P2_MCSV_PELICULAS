package com.movies.microservice.poster.infrastructure.inputadapters.rest.mapper;

import com.movies.microservice.poster.domain.entities.Poster;
import com.movies.microservice.poster.infrastructure.inputadapters.rest.dto.PosterResponse;

public class PosterRestMapper {

    public static PosterResponse toResponse(Poster p) {
        return PosterResponse.builder()
                .id(p.getId())
                .peliculaId(p.getPeliculaId())
                .url(p.getUrl())
                .activo(p.isActivo())
                .orden(p.getOrden())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}
