package com.movies.microservice.categoria.application.inputports;

import com.movies.microservice.categoria.domain.entities.Categoria;
import java.util.UUID;

public interface EditarCategoriaInputPort {

    Categoria editar(UUID categoriaId, String nuevoNombre);
}
