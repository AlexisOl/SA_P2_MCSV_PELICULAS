package com.movies.microservice.horario.application.inputports;

import com.movies.microservice.horario.domain.entities.Horario;

public interface CrearHorarioInputPort {

    Horario crear(Horario horario);
}
