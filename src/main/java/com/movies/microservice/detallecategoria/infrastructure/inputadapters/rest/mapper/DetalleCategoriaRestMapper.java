package com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.mapper;

import com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto.CategoriaDePeliculaResponse;
import com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto.PeliculaPorCategoriaResponse;

import java.util.UUID;

public class DetalleCategoriaRestMapper {

    public static CategoriaDePeliculaResponse toCategoriaResp(UUID categoriaId, String nombre) {
        return CategoriaDePeliculaResponse.builder()
                .categoriaId(categoriaId)
                .nombre(nombre) // puedes pasar null si no lo resuelves aún
                .build();
    }

    public static PeliculaPorCategoriaResponse toPeliculaResp(UUID peliculaId, String titulo) {
        return PeliculaPorCategoriaResponse.builder()
                .peliculaId(peliculaId)
                .titulo(titulo)
                .build();
    }
}
