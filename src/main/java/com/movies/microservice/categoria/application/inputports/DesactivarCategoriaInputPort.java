package com.movies.microservice.categoria.application.inputports;

import com.movies.microservice.categoria.domain.entities.Categoria;
import java.util.UUID;

public interface DesactivarCategoriaInputPort {

    Categoria desactivar(UUID categoriaId);
}
