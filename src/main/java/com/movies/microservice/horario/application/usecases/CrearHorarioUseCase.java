package com.movies.microservice.horario.application.usecases;

import com.movies.microservice.horario.application.inputports.CrearHorarioInputPort;
import com.movies.microservice.horario.application.outputports.persistence.HorarioRepositorioOutputPort;
import com.movies.microservice.horario.application.outputports.query.ValidacionesExternaOutputPort;
import com.movies.microservice.horario.domain.entities.Horario;
import com.movies.microservice.horario.domain.exceptions.HorarioOverlapException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Service
@RequiredArgsConstructor
public class CrearHorarioUseCase implements CrearHorarioInputPort {

    private final HorarioRepositorioOutputPort repo;
    private final ValidacionesExternaOutputPort validaciones;

    @Override
    public Horario crear(Horario horario) {
        horario.validar();

        if (!validaciones.existePelicula(horario.getPeliculaId())) {
            throw new IllegalArgumentException("Película no existe");
        }

        if (!validaciones.existeCinema(horario.getCinemaId())) {
            throw new IllegalArgumentException("Cine no existe");
        }

        if (!validaciones.existeSala(horario.getSalaId())) {
            throw new IllegalArgumentException("Sala no existe");
        }

        long traslapes = repo.countTraslapes(
                horario.getSalaId(), horario.getInicio(), horario.getFin());
        if (traslapes > 0) {
            throw new HorarioOverlapException("El horario se traslapa con otro existente");
        }

        horario.setId(UUID.randomUUID());
        horario.setActivo(true);

        return repo.save(horario);
    }
}
