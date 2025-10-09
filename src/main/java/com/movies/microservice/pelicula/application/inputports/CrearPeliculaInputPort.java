package com.movies.microservice.pelicula.application.inputports;

import com.movies.microservice.pelicula.application.commands.CrearPeliculaCommand;
import com.movies.microservice.pelicula.domain.Pelicula;

public interface CrearPeliculaInputPort {

    Pelicula crear(CrearPeliculaCommand cmd);
}
