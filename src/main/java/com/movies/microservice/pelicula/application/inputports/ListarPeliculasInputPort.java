package com.movies.microservice.pelicula.application.inputports;

import com.movies.microservice.pelicula.application.commands.ListarPeliculasQuery;
import com.movies.microservice.pelicula.domain.Pelicula;
import java.util.List;

public interface ListarPeliculasInputPort {

    List<Pelicula> listar(ListarPeliculasQuery query);
}
