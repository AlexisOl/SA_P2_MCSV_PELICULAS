package com.movies.microservice.horario.infrastructure.eventos.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificarHorarioDto {

    private UUID funcionId;
    private UUID salaId;
    private Integer fila;
    private Integer columna;

}
