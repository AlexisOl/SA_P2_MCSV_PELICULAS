package com.movies.microservice.horario.application.usecases;

import com.movies.microservice.horario.application.inputports.ActualizarHorarioInputPort;
import com.movies.microservice.horario.application.outputports.persistence.HorarioRepositorioOutputPort;
import com.movies.microservice.horario.domain.entities.Horario;
import com.movies.microservice.horario.domain.exceptions.HorarioNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Service
@RequiredArgsConstructor
public class ActualizarHorarioUseCase implements ActualizarHorarioInputPort {

    private final HorarioRepositorioOutputPort repo;

    @Override
    public Horario actualizar(UUID horarioId, Horario datos) {
        var existente = repo.findById(horarioId)
                .orElseThrow(() -> new HorarioNotFoundException("Horario no encontrado"));

        existente.setIdioma(datos.getIdioma());
        existente.setFormato(datos.getFormato());
        existente.setInicio(datos.getInicio());
        existente.setFin(datos.getFin());
        existente.setPrecio(datos.getPrecio());
        existente.validar();

        return repo.save(existente);
    }
}
