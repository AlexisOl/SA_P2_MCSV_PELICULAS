package com.movies.microservice.horario.infrastructure.outputadapters.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movies.microservice.horario.infrastructure.outputadapters.persistence.entity.HorarioJpaEntity;

public interface JpaHorarioRepository extends JpaRepository<HorarioJpaEntity, UUID> {

    @Query("""
    SELECT COUNT(h) FROM HorarioJpaEntity h
    WHERE h.salaId = :salaId
      AND h.activo = TRUE
      AND (h.inicio < :fin AND h.fin > :inicio)
  """)
    long countTraslapes(UUID salaId, LocalDateTime inicio, LocalDateTime fin);

    @Query("""
    SELECT h FROM HorarioJpaEntity h
    WHERE (:peliculaId IS NULL OR h.peliculaId = :peliculaId)
      AND (:cinemaId   IS NULL OR h.cinemaId   = :cinemaId)
      AND (:desde      IS NULL OR h.inicio    >= :desde)
      AND (:hasta      IS NULL OR h.inicio    <= :hasta)
      AND (:soloActivos = FALSE OR h.activo = TRUE)
    ORDER BY h.inicio ASC
  """)
    List<HorarioJpaEntity> search(UUID peliculaId, UUID cinemaId,
            LocalDateTime desde, LocalDateTime hasta,
            boolean soloActivos);
}
