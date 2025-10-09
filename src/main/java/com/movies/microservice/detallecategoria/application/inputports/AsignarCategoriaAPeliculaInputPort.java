package com.movies.microservice.detallecategoria.application.inputports;

import java.util.UUID;

public interface AsignarCategoriaAPeliculaInputPort {

    void asignar(UUID peliculaId, UUID categoriaId);
}
