package com.movies.microservice.horario.infrastructure.inputadapters.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CrearActualizarHorarioRequest {

    private UUID peliculaId;
    private UUID cinemaId;
    private UUID salaId;
    private String idioma;          // ES/EN/SUB/DOB
    private String formato;         // 2D/3D/IMAX
    private LocalDateTime inicio;   // ISO: 2025-12-10T18:30:00
    private LocalDateTime fin;      // ISO
    private BigDecimal precio;
}
