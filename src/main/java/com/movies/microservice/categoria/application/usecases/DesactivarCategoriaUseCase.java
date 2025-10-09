package com.movies.microservice.categoria.application.usecases;

import com.movies.microservice.categoria.application.inputports.DesactivarCategoriaInputPort;
import com.movies.microservice.categoria.application.outputports.messaging.CategoriaEventPublisherOutputPort;
import com.movies.microservice.categoria.application.outputports.persistence.CategoriaRepositorioOutputPort;
import com.movies.microservice.categoria.domain.entities.Categoria;
import com.movies.microservice.categoria.domain.events.CategoriaDesactivadaEvent;
import com.movies.microservice.categoria.domain.exceptions.CategoriaNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class DesactivarCategoriaUseCase implements DesactivarCategoriaInputPort {

    private final CategoriaRepositorioOutputPort repo;
    private final CategoriaEventPublisherOutputPort publisher;

    @Override
    public Categoria desactivar(UUID categoriaId) {
        var categoria = repo.findById(categoriaId)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría no encontrada"));
        if (categoria.isActiva()) {
            categoria.desactivar();
            categoria.setUpdatedAt(LocalDateTime.now());
            categoria = repo.save(categoria);
            publisher.publish(new CategoriaDesactivadaEvent(categoria.getId()));
        }
        return categoria;
    }
}
