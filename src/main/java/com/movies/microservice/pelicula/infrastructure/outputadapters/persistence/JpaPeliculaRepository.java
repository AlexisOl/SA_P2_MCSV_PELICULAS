package com.movies.microservice.pelicula.infrastructure.outputadapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface JpaPeliculaRepository extends JpaRepository<PeliculaJpaEntity, UUID> {

    @Query("""
      SELECT CASE WHEN COUNT(p)>0 THEN TRUE ELSE FALSE END
      FROM PeliculaJpaEntity p
      WHERE p.titulo = :titulo AND p.fechaEstreno = :releaseDate
    """)
    boolean existsTituloAndReleaseDate(String titulo, LocalDate releaseDate);

    @Query("""
      SELECT p FROM PeliculaJpaEntity p
      WHERE (:texto IS NULL OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :texto, '%')))
        AND (:clasificacion IS NULL OR p.clasificacion = :clasificacion)
        AND (:activa IS NULL OR p.activa = :activa)
        AND (:releasedFrom IS NULL OR p.fechaEstreno >= :releasedFrom)
        AND (:releasedTo   IS NULL OR p.fechaEstreno <= :releasedTo)
      ORDER BY p.fechaEstreno DESC
    """)
    List<PeliculaJpaEntity> search(String texto, String clasificacion, Boolean activa,
            LocalDate releasedFrom, LocalDate releasedTo);
}
