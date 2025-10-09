package com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class AsignacionRequest {

    private UUID categoriaId; // requerida para asignar/quitar una
}
