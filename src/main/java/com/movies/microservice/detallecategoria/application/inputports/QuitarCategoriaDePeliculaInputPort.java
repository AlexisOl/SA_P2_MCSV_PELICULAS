package com.movies.microservice.detallecategoria.application.inputports;

import java.util.UUID;

public interface QuitarCategoriaDePeliculaInputPort {

    void quitar(UUID peliculaId, UUID categoriaId);
}
