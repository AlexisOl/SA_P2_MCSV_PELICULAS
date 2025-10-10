package com.movies.microservice.poster.infrastructure.outputadapters.persistence.repository;

import com.movies.microservice.poster.application.outputports.persistence.PosterRepositorioOutputPort;
import com.movies.microservice.poster.domain.entities.Poster;
import com.movies.microservice.poster.infrastructure.outputadapters.persistence.entity.PosterJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class PosterRepositoryAdapter implements PosterRepositorioOutputPort {

    private final JpaPosterRepository jpa;

    @Override
    public Poster save(Poster poster) {
        var entity = toEntity(poster);
        var saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Poster> findById(UUID posterId) {
        return jpa.findById(posterId).map(this::toDomain);
    }

    @Override
    public List<Poster> findByPelicula(UUID peliculaId, Boolean activosSolo) {
        boolean onlyActive = activosSolo != null && activosSolo;
        return jpa.findByPelicula(peliculaId, onlyActive).stream().map(this::toDomain).toList();
    }

    @Override
    public boolean existsByPeliculaAndUrl(UUID peliculaId, String url) {
        return jpa.existsByPeliculaIdAndUrl(peliculaId, url);
    }

    @Override
    public void delete(UUID posterId) {
        jpa.deleteById(posterId);
    }

    // ---- mapping
    private PosterJpaEntity toEntity(Poster p) {
        return PosterJpaEntity.builder()
                .id(p.getId())
                .peliculaId(p.getPeliculaId())
                .url(p.getUrl())
                .activo(p.isActivo())
                .orden(p.getOrden())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }

    private Poster toDomain(PosterJpaEntity e) {
        return Poster.builder()
                .id(e.getId())
                .peliculaId(e.getPeliculaId())
                .url(e.getUrl())
                .activo(e.isActivo())
                .orden(e.getOrden())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
