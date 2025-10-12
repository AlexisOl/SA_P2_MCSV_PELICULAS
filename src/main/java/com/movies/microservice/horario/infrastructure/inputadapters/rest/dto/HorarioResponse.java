package com.movies.microservice.horario.infrastructure.inputadapters.rest.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class HorarioResponse {

    UUID id;
    UUID peliculaId;
    UUID cinemaId;
    UUID salaId;
    String idioma;
    String formato;
    LocalDateTime inicio;
    LocalDateTime fin;
    BigDecimal precio;
    boolean activo;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
