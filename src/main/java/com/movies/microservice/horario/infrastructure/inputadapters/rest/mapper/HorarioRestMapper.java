package com.movies.microservice.horario.infrastructure.inputadapters.rest.mapper;

import com.movies.microservice.horario.domain.entities.Horario;
import com.movies.microservice.horario.infrastructure.inputadapters.rest.dto.CrearActualizarHorarioRequest;
import com.movies.microservice.horario.infrastructure.inputadapters.rest.dto.HorarioResponse;

public class HorarioRestMapper {

    public static Horario toDomain(CrearActualizarHorarioRequest r) {
        return Horario.builder()
                .peliculaId(r.getPeliculaId())
                .cinemaId(r.getCinemaId())
                .salaId(r.getSalaId())
                .idioma(r.getIdioma())
                .formato(r.getFormato())
                .inicio(r.getInicio())
                .fin(r.getFin())
                .precio(r.getPrecio())
                .build();
    }

    public static HorarioResponse toResponse(Horario h) {
        return HorarioResponse.builder()
                .id(h.getId())
                .peliculaId(h.getPeliculaId())
                .cinemaId(h.getCinemaId())
                .salaId(h.getSalaId())
                .idioma(h.getIdioma())
                .formato(h.getFormato())
                .inicio(h.getInicio())
                .fin(h.getFin())
                .precio(h.getPrecio())
                .activo(h.isActivo())
                .createdAt(h.getCreatedAt())
                .updatedAt(h.getUpdatedAt())
                .build();
    }
}
