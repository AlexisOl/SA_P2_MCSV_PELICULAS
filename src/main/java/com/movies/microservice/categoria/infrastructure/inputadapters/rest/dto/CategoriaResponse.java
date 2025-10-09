package com.movies.microservice.categoria.infrastructure.inputadapters.rest.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class CategoriaResponse {

    UUID id;
    String nombre;
    boolean activa;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
