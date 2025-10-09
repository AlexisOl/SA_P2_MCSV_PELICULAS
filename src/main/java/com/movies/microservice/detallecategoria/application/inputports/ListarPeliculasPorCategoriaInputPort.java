package com.movies.microservice.detallecategoria.application.inputports;

import java.util.List;
import java.util.UUID;

public interface ListarPeliculasPorCategoriaInputPort {

    List<UUID> listarPeliculas(UUID categoriaId);
}
