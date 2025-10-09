package com.movies.microservice.detallecategoria.application.inputports;

import java.util.Set;
import java.util.UUID;

public interface ReemplazarCategoriasDePeliculaInputPort {

    void reemplazar(UUID peliculaId, Set<UUID> nuevasCategorias);
}
