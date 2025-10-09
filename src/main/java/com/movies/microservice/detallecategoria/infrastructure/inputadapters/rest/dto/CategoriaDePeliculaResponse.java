package com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class CategoriaDePeliculaResponse {

    UUID categoriaId;
    String nombre; // opcional (cuando quieras enriquecer desde hexágono Categoría)
}
