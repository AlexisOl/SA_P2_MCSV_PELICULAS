package com.movies.microservice.categoria.infrastructure.outputadapters.persistence;

import com.movies.microservice.categoria.application.outputports.persistence.CategoriaRepositorioOutputPort;
import com.movies.microservice.categoria.domain.entities.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoriaRepositoryAdapter implements CategoriaRepositorioOutputPort {

    private final JpaCategoriaRepository jpa;

    @Override
    public Categoria save(Categoria c) {
        CategoriaJpaEntity entity = toEntity(c);
        CategoriaJpaEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Categoria> findById(UUID id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return jpa.existsByNombreIgnoreCase(nombre);
    }

    @Override
    public List<Categoria> findAll(Boolean active) {
        return jpa.findAllByActiva(active).stream().map(this::toDomain).toList();
    }

    // === Mapping ===
    private CategoriaJpaEntity toEntity(Categoria c) {
        return CategoriaJpaEntity.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .activa(c.isActiva())
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }

    private Categoria toDomain(CategoriaJpaEntity e) {
        return Categoria.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .activa(e.isActiva())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
