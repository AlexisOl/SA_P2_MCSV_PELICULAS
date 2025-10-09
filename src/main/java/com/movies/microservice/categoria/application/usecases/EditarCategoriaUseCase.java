package com.movies.microservice.categoria.application.usecases;

import com.movies.microservice.categoria.application.inputports.EditarCategoriaInputPort;
import com.movies.microservice.categoria.application.outputports.messaging.CategoriaEventPublisherOutputPort;
import com.movies.microservice.categoria.application.outputports.persistence.CategoriaRepositorioOutputPort;
import com.movies.microservice.categoria.domain.entities.Categoria;
import com.movies.microservice.categoria.domain.events.CategoriaActualizadaEvent;
import com.movies.microservice.categoria.domain.exceptions.CategoriaNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class EditarCategoriaUseCase implements EditarCategoriaInputPort {

    private final CategoriaRepositorioOutputPort repo;
    private final CategoriaEventPublisherOutputPort publisher;

    @Override
    public Categoria editar(UUID categoriaId, String nuevoNombre) {
        if (categoriaId == null) {
            throw new IllegalArgumentException("categoriaId es obligatorio");
        }
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        var categoria = repo.findById(categoriaId)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría no encontrada"));

        var limpio = nuevoNombre.trim();
        if (!limpio.equalsIgnoreCase(categoria.getNombre()) && repo.existsByNombre(limpio)) {
            // si es distinto al actual, valida unicidad
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + limpio);
        }

        categoria.editarNombre(limpio);
        categoria.setUpdatedAt(LocalDateTime.now());

        var updated = repo.save(categoria);
        publisher.publish(new CategoriaActualizadaEvent(updated.getId()));
        return updated;
    }
}
