package com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto;

import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Data
public class ReemplazoRequest {

    private Set<UUID> categoriaIds; // set completo a dejar en la película
}
