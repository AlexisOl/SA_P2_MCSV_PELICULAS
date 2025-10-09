package com.movies.microservice.categoria.application.usecases;

import com.movies.microservice.categoria.application.inputports.ListarCategoriasInputPort;
import com.movies.microservice.categoria.application.outputports.persistence.CategoriaRepositorioOutputPort;
import com.movies.microservice.categoria.domain.entities.Categoria;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarCategoriasUseCase implements ListarCategoriasInputPort {

    private final CategoriaRepositorioOutputPort repo;

    @Override
    public List<Categoria> listar(Boolean active) {
        return repo.findAll(active);
    }
}
