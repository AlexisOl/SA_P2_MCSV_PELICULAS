package com.movies.microservice.detallecategoria.application.usecases;

import com.movies.microservice.detallecategoria.application.inputports.QuitarCategoriaDePeliculaInputPort;
import com.movies.microservice.detallecategoria.application.outputports.messaging.DetalleCategoriaEventPublisherOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.persistence.DetalleCategoriaRepositorioOutputPort;
import com.movies.microservice.detallecategoria.domain.exceptions.DetalleCategoriaNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class QuitarCategoriaDePeliculaUseCase implements QuitarCategoriaDePeliculaInputPort {

    private final DetalleCategoriaRepositorioOutputPort repo;
    private final DetalleCategoriaEventPublisherOutputPort publisher;

    @Override
    @Transactional
    public void quitar(UUID peliculaId, UUID categoriaId) {
        if (!repo.exists(peliculaId, categoriaId)) {
            throw new DetalleCategoriaNotFoundException("La categoría no está asignada a la película");
        }
        repo.delete(peliculaId, categoriaId);
        publisher.publishCategoriaRemovida(peliculaId, categoriaId);
    }
}
