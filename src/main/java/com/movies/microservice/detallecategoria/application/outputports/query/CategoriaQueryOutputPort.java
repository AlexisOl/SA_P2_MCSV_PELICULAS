package com.movies.microservice.detallecategoria.application.outputports.query;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface CategoriaQueryOutputPort {

    boolean existsById(UUID categoriaId);

    boolean isActive(UUID categoriaId);   // solo categorías activas pueden asignarse
    
    Map<UUID, String> findNamesByIds(Set<UUID> ids);
}
