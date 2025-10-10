package com.movies.microservice.poster.infrastructure.inputadapters.rest.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class PosterResponse {

    UUID id;
    UUID peliculaId;
    String url;
    boolean activo;
    Integer orden;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
