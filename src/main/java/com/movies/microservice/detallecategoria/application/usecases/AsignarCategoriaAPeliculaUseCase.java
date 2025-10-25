package com.movies.microservice.detallecategoria.application.usecases;

import com.movies.microservice.detallecategoria.application.inputports.AsignarCategoriaAPeliculaInputPort;
import com.movies.microservice.detallecategoria.application.outputports.messaging.DetalleCategoriaEventPublisherOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.persistence.DetalleCategoriaRepositorioOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.query.CategoriaQueryOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.query.PeliculaExistenciaOutputPort;
import com.movies.microservice.detallecategoria.domain.exceptions.DetalleCategoriaAlreadyExistsException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AsignarCategoriaAPeliculaUseCase implements AsignarCategoriaAPeliculaInputPort {

    private final DetalleCategoriaRepositorioOutputPort repo;
    private final PeliculaExistenciaOutputPort peliculaQuery;
    private final CategoriaQueryOutputPort categoriaQuery;
    private final DetalleCategoriaEventPublisherOutputPort publisher;

    @Override
    public void asignar(UUID peliculaId, UUID categoriaId) {
        if (peliculaId == null || categoriaId == null) {
            throw new IllegalArgumentException("IDs obligatorios");
        }
        if (!peliculaQuery.existsById(peliculaId)) {
            throw new IllegalArgumentException("Película no existe");
        }
        if (!categoriaQuery.existsById(categoriaId)) {
            throw new IllegalArgumentException("Categoría no existe");
        }
        if (!categoriaQuery.isActive(categoriaId)) {
            throw new IllegalArgumentException("Categoría inactiva");
        }

        if (repo.exists(peliculaId, categoriaId)) {
            throw new DetalleCategoriaAlreadyExistsException("La categoría ya está asignada a la película");
        }

        repo.save(peliculaId, categoriaId);
        publisher.publishCategoriaAsignada(peliculaId, categoriaId);
    }
}
