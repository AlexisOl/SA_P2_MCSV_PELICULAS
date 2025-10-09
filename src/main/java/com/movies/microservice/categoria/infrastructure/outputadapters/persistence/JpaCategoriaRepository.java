package com.movies.microservice.categoria.infrastructure.outputadapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaCategoriaRepository extends JpaRepository<CategoriaJpaEntity, UUID> {

    boolean existsByNombreIgnoreCase(String nombre);

    @Query("""
      SELECT c FROM CategoriaJpaEntity c
      WHERE (:activa IS NULL OR c.activa = :activa)
      ORDER BY c.nombre ASC
    """)
    List<CategoriaJpaEntity> findAllByActiva(Boolean activa);
}
