package com.movies.microservice.pelicula.application.commands;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class ListarPeliculasQuery {

    String texto;
    String clasificacion;      // como string para filtro rápido
    Boolean activa;
    LocalDate releasedFrom;
    LocalDate releasedTo;
    List<String> categorias;
    int page;
    int size;
    String sort;               // ej: "releaseDate,desc"
}
