package com.movies.microservice.detallecategoria.infrastructure.outputadapters.persistence;

import com.movies.microservice.detallecategoria.application.outputports.persistence.DetalleCategoriaRepositorioOutputPort;
import com.movies.microservice.detallecategoria.infrastructure.outputadapters.persistence.entity.MovieCategoryJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DetalleCategoriaRepositoryAdapter implements DetalleCategoriaRepositorioOutputPort {

    private final JpaMovieCategoryRepository jpa;

    @Override
    public boolean exists(UUID peliculaId, UUID categoriaId) {
        return jpa.existsVinculo(peliculaId, categoriaId);
    }

    @Override
    public void save(UUID peliculaId, UUID categoriaId) {
        var entity = MovieCategoryJpaEntity.builder()
                .id(UUID.randomUUID())
                .movieId(peliculaId)
                .categoryId(categoriaId)
                .build();
        jpa.save(entity);
    }

    @Override
    public void delete(UUID peliculaId, UUID categoriaId) {
        jpa.findByMovieIdAndCategoryId(peliculaId, categoriaId).ifPresent(jpa::delete);
    }

    @Override
    public List<UUID> findCategoriasByPelicula(UUID peliculaId) {
        return jpa.findCategoriasByPelicula(peliculaId);
    }

    @Override
    public List<UUID> findPeliculasByCategoria(UUID categoriaId) {
        return jpa.findPeliculasByCategoria(categoriaId);
    }

    @Override
    public void saveAll(UUID peliculaId, Set<UUID> categoriaIds) {
        for (UUID cid : categoriaIds) {
            save(peliculaId, cid);
        }
    }

    @Override
    public void deleteAll(UUID peliculaId, Set<UUID> categoriaIds) {
        for (UUID cid : categoriaIds) {
            delete(peliculaId, cid);
        }
    }
}
