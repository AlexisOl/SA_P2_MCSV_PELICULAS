package com.movies.microservice.pelicula.application.commands;

import com.movies.microservice.pelicula.domain.valueobjects.Clasificacion;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class ActualizarPeliculaCommand {

    UUID peliculaId;
    String titulo;
    String sinopsis;
    Integer duracion;              // null si no cambia
    List<String> posters;          // reemplazo completo (o null para no tocar)
    List<String> cast;
    String director;
    Clasificacion clasificacion;
    LocalDate fechaEstreno;
    List<String> categorias;
    Boolean activa;                // opcional: si decides permitirlo desde este caso
}
