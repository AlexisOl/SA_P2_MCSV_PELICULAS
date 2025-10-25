package com.movies.microservice.poster.infrastructure.outputadapters.persistence.repository;

import com.movies.microservice.poster.infrastructure.outputadapters.persistence.entity.PosterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaPosterRepository extends JpaRepository<PosterJpaEntity, UUID> {

    @Query("""
      SELECT p FROM PosterJpaEntity p
      WHERE p.peliculaId = :peliculaId
        AND (:activosSolo = FALSE OR p.activo = TRUE)
      ORDER BY p.orden ASC, p.createdAt ASC
    """)
    List<PosterJpaEntity> findByPelicula(UUID peliculaId, boolean activosSolo);

    boolean existsByPeliculaIdAndUrl(UUID peliculaId, String url);
}
