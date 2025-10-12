package com.movies.microservice.horario.application.inputports;

import com.movies.microservice.horario.domain.entities.Horario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ListarHorariosInputPort {

    List<Horario> listar(UUID peliculaId, UUID cinemaId,
            LocalDateTime desde, LocalDateTime hasta,
            Boolean soloActivos);
}
