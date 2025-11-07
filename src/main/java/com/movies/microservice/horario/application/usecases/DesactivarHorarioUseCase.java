package com.movies.microservice.horario.application.usecases;

import com.movies.microservice.horario.application.inputports.*;
import com.movies.microservice.horario.application.outputports.persistence.HorarioRepositorioOutputPort;
import com.movies.microservice.horario.domain.entities.Horario;
import com.movies.microservice.horario.domain.exceptions.HorarioNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

//@Service
@RequiredArgsConstructor
public class DesactivarHorarioUseCase implements DesactivarHorarioInputPort {

    private final HorarioRepositorioOutputPort repo;
    
    @Transactional
    @Override
    public Horario desactivar(UUID horarioId) {
        var horario = repo.findById(horarioId)
                .orElseThrow(() -> new HorarioNotFoundException("Horario no encontrado"));
        horario.desactivar();
        return repo.save(horario);
    }
}
