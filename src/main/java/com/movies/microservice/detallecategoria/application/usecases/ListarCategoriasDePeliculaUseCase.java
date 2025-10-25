package com.movies.microservice.detallecategoria.application.usecases;

import com.movies.microservice.detallecategoria.application.inputports.ListarCategoriasDePeliculaInputPort;
import com.movies.microservice.detallecategoria.application.outputports.persistence.DetalleCategoriaRepositorioOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ListarCategoriasDePeliculaUseCase implements ListarCategoriasDePeliculaInputPort {

    private final DetalleCategoriaRepositorioOutputPort repo;

    @Override
    public List<UUID> listarCategorias(UUID peliculaId) {
        return repo.findCategoriasByPelicula(peliculaId);
    }
}
