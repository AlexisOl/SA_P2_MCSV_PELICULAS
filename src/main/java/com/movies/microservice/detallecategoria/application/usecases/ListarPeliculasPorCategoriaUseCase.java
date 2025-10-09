package com.movies.microservice.detallecategoria.application.usecases;

import com.movies.microservice.detallecategoria.application.inputports.ListarPeliculasPorCategoriaInputPort;
import com.movies.microservice.detallecategoria.application.outputports.persistence.DetalleCategoriaRepositorioOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ListarPeliculasPorCategoriaUseCase implements ListarPeliculasPorCategoriaInputPort {

    private final DetalleCategoriaRepositorioOutputPort repo;

    @Override
    public List<UUID> listarPeliculas(UUID categoriaId) {
        return repo.findPeliculasByCategoria(categoriaId);
    }
}
