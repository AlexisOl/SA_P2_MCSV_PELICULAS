package com.movies.microservice.horario.application.outputports.persistence;

import com.movies.microservice.horario.domain.entities.Horario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HorarioRepositorioOutputPort {

    Horario save(Horario horario);

    Optional<Horario> findById(UUID id);

    void delete(UUID id);

    List<Horario> search(UUID peliculaId, UUID cinemaId,
            LocalDateTime desde, LocalDateTime hasta,
            boolean soloActivos);

    long countTraslapes(UUID salaId, LocalDateTime inicio, LocalDateTime fin);
}
