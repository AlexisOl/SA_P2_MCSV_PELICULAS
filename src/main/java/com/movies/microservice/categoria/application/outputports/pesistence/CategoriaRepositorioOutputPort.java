package com.movies.microservice.categoria.application.outputports.persistence;

import com.movies.microservice.categoria.domain.entities.Categoria;
import java.util.*;

public interface CategoriaRepositorioOutputPort {

    Categoria save(Categoria categoria);          // insert o update

    Optional<Categoria> findById(UUID id);

    boolean existsByNombre(String nombre);        // para validar unicidad (case-insensitive según DB)

    List<Categoria> findAll(Boolean active);      // si active==null -> todas
}
