package com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto;

import com.movies.microservice.pelicula.domain.valueobjects.Clasificacion;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class PeliculaResponse {

    UUID id;
    String titulo;
    String sinopsis;
    int duracion;
    List<String> posters;
    List<String> cast;
    String director;
    Clasificacion clasificacion;
    boolean activa;
    LocalDate fechaEstreno;
}
