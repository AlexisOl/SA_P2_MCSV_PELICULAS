package com.movies.microservice.detallecategoria.application.inputports;

import java.util.List;
import java.util.UUID;

public interface ListarCategoriasDePeliculaInputPort {

    List<UUID> listarCategorias(UUID peliculaId);
}
