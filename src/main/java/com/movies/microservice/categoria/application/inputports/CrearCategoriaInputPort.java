package com.movies.microservice.categoria.application.inputports;

import com.movies.microservice.categoria.domain.entities.Categoria;

public interface CrearCategoriaInputPort {

    Categoria crear(String nombre, boolean activa);
}
