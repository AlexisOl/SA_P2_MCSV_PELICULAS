package com.movies.microservice.pelicula.application.inputports;

import com.movies.microservice.pelicula.application.commands.ActualizarPeliculaCommand;
import com.movies.microservice.pelicula.domain.Pelicula;

public interface ActualizarPeliculaInputPort {

    Pelicula actualizar(ActualizarPeliculaCommand cmd);
}
