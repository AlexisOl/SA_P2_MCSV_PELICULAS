package com.movies.microservice.horario.application.outputports.eventos;

import com.movies.microservice.horario.domain.entities.Horario;

public interface NotificarHorarioOutputPort {

    void notificarHorarioCreado(Horario horario, Integer fila, Integer columna);
}
