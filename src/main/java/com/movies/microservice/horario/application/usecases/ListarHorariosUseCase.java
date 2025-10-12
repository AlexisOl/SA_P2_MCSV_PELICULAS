package com.movies.microservice.horario.application.usecases;

import com.movies.microservice.horario.application.inputports.ListarHorariosInputPort;
import com.movies.microservice.horario.application.outputports.persistence.HorarioRepositorioOutputPort;
import com.movies.microservice.horario.domain.entities.Horario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//@Service
@RequiredArgsConstructor
public class ListarHorariosUseCase implements ListarHorariosInputPort {

    private final HorarioRepositorioOutputPort repo;

    @Override
    public List<Horario> listar(UUID peliculaId, UUID cinemaId,
            LocalDateTime desde, LocalDateTime hasta,
            Boolean soloActivos) {
        boolean activos = soloActivos != null && soloActivos;
        return repo.search(peliculaId, cinemaId, desde, hasta, activos);
    }
}
