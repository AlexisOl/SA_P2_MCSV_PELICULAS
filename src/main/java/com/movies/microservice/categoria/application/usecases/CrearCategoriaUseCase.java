package com.movies.microservice.categoria.application.usecases;

import com.movies.microservice.categoria.application.inputports.CrearCategoriaInputPort;
import com.movies.microservice.categoria.application.outputports.messaging.CategoriaEventPublisherOutputPort;
import com.movies.microservice.categoria.application.outputports.persistence.CategoriaRepositorioOutputPort;
import com.movies.microservice.categoria.domain.entities.Categoria;
import com.movies.microservice.categoria.domain.events.CategoriaCreadaEvent;
import com.movies.microservice.categoria.domain.exceptions.CategoriaAlreadyExistsException;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CrearCategoriaUseCase implements CrearCategoriaInputPort {

    private final CategoriaRepositorioOutputPort repo;
    private final CategoriaEventPublisherOutputPort publisher;

    @Override
    @Transactional
    public Categoria crear(String nombre, boolean activa) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        var limpio = nombre.trim();

        if (repo.existsByNombre(limpio)) {
            throw new CategoriaAlreadyExistsException(limpio);
        }

        var now = LocalDateTime.now();
        var categoria = Categoria.builder()
                .id(UUID.randomUUID())
                .nombre(limpio)
                .activa(activa)
                .createdAt(now)
                .updatedAt(now)
                .build();

        var saved = repo.save(categoria);
        publisher.publish(new CategoriaCreadaEvent(saved.getId()));
        return saved;
    }
}
