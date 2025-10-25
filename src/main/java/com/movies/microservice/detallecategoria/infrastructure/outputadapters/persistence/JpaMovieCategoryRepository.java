package com.movies.microservice.detallecategoria.infrastructure.outputadapters.persistence;

import com.movies.microservice.detallecategoria.infrastructure.outputadapters.persistence.entity.MovieCategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaMovieCategoryRepository extends JpaRepository<MovieCategoryJpaEntity, UUID> {

    @Query("""
      SELECT CASE WHEN COUNT(mc)>0 THEN TRUE ELSE FALSE END
      FROM MovieCategoryJpaEntity mc
      WHERE mc.movieId = :peliculaId AND mc.categoryId = :categoriaId
    """)
    boolean existsVinculo(UUID peliculaId, UUID categoriaId);

    @Query("SELECT mc.categoryId FROM MovieCategoryJpaEntity mc WHERE mc.movieId = :peliculaId")
    List<UUID> findCategoriasByPelicula(UUID peliculaId);

    @Query("SELECT mc.movieId FROM MovieCategoryJpaEntity mc WHERE mc.categoryId = :categoriaId")
    List<UUID> findPeliculasByCategoria(UUID categoriaId);

    Optional<MovieCategoryJpaEntity> findByMovieIdAndCategoryId(UUID movieId, UUID categoryId);
}
