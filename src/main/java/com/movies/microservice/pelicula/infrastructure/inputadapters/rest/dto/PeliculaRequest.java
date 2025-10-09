package com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto;

import com.movies.microservice.pelicula.domain.valueobjects.Clasificacion;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PeliculaRequest {

    private String titulo;
    private String sinopsis;
    private Integer duracion;          // minutos
    private List<String> posters;      // URLs (si luego los llevamos a hexágono Poster, lo ignoramos acá)
    private List<String> cast;         // nombres
    private String director;
    private Clasificacion clasificacion;
    private Boolean activa;
    private LocalDate fechaEstreno;
    private List<String> categorias;   // se integrará con hexágono Categoría
}
