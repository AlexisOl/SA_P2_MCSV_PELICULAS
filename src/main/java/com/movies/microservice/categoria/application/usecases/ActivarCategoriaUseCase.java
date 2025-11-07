package com.movies.microservice.categoria.application.usecases;

import com.movies.microservice.categoria.application.inputports.ActivarCategoriaInputPort;
import com.movies.microservice.categoria.application.outputports.messaging.CategoriaEventPublisherOutputPort;
import com.movies.microservice.categoria.application.outputports.persistence.CategoriaRepositorioOutputPort;
import com.movies.microservice.categoria.domain.entities.Categoria;
import com.movies.microservice.categoria.domain.events.CategoriaActivadaEvent;
import com.movies.microservice.categoria.domain.exceptions.CategoriaNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class ActivarCategoriaUseCase implements ActivarCategoriaInputPort {

    private final CategoriaRepositorioOutputPort repo;
    private final CategoriaEventPublisherOutputPort publisher;

    @Override
    public Categoria activar(UUID categoriaId) {
        var categoria = repo.findById(categoriaId)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría no encontrada"));
        if (!categoria.isActiva()) {
            categoria.activar();
            categoria.setUpdatedAt(LocalDateTime.now());
            categoria = repo.save(categoria);
            publisher.publish(new CategoriaActivadaEvent(categoria.getId()));
        }
        return categoria;
    }
}
