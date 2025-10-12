package com.movies.microservice.horario.application.inputports;

import com.movies.microservice.horario.domain.entities.Horario;
import java.util.UUID;

public interface DesactivarHorarioInputPort {

    Horario desactivar(UUID horarioId);
}
