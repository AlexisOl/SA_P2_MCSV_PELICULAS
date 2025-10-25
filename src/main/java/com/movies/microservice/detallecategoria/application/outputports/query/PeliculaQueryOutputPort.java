package com.movies.microservice.detallecategoria.application.outputports.query;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface PeliculaQueryOutputPort {
    Map<UUID, String> findTitlesByIds(Set<UUID> ids);
}
