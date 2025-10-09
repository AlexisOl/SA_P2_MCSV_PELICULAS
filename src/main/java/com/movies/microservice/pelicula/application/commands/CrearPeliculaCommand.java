package com.movies.microservice.pelicula.application.commands;

import com.movies.microservice.pelicula.domain.valueobjects.Clasificacion;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class CrearPeliculaCommand {

    String titulo;
    String sinopsis;
    int duracion;                  // minutos
    List<String> posters;          // URLs
    List<String> cast;             // nombres simple; si luego usas {name,role}, lo cambiamos
    String director;
    Clasificacion clasificacion;
    boolean activa;
    LocalDate fechaEstreno;
    List<String> categorias;       // nombres o IDs según cómo lo modeles en el hexágono Categoría
}
