package com.movies.microservice.pelicula.application.usecases;

import com.movies.microservice.pelicula.application.commands.ListarPeliculasQuery;
import com.movies.microservice.pelicula.application.inputports.ListarPeliculasInputPort;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.domain.Pelicula;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarPeliculasUseCase implements ListarPeliculasInputPort {

    private final @NonNull
    PeliculaRepositorioOutputPort repo;

    @Override
    public List<Pelicula> listar(ListarPeliculasQuery q) {
        return repo.search(
                q.getTexto(),
                q.getClasificacion(),
                q.getActiva(),
                q.getReleasedFrom(),
                q.getReleasedTo(),
                q.getCategorias(),
                q.getPage(),
                q.getSize(),
                q.getSort()
        );
    }
}
