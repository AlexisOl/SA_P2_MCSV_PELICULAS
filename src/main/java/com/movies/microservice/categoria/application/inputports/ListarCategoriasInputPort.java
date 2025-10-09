package com.movies.microservice.categoria.application.inputports;

import com.movies.microservice.categoria.domain.entities.Categoria;
import java.util.List;

public interface ListarCategoriasInputPort {

    // active: true/false para filtrar; null = todas
    List<Categoria> listar(Boolean active);
}
