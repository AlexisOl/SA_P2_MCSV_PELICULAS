package com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class PeliculaPorCategoriaResponse {

    UUID peliculaId;
    String titulo; // opcional (cuando quieras enriquecer desde hexágono Película)
}
