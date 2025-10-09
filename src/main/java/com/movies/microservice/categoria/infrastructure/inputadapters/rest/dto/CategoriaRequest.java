package com.movies.microservice.categoria.infrastructure.inputadapters.rest.dto;

import lombok.Data;

@Data
public class CategoriaRequest {

    private String nombre;
    private Boolean activa;  // opcional, por defecto true
}
