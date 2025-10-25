package com.movies.microservice.detallecategoria.application.outputports.persistence;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface DetalleCategoriaRepositorioOutputPort {

    boolean exists(UUID peliculaId, UUID categoriaId);

    void save(UUID peliculaId, UUID categoriaId);                // inserta vínculo

    void delete(UUID peliculaId, UUID categoriaId);              // elimina vínculo exacto

    List<UUID> findCategoriasByPelicula(UUID peliculaId);        // devuelve categoryIds

    List<UUID> findPeliculasByCategoria(UUID categoriaId);       // devuelve movieIds

    // Opcionales de batch (pueden ser no-op y la UC hará loop):
    default void saveAll(UUID peliculaId, Set<UUID> categoriaIds) {
        for (UUID cid : categoriaIds) {
            save(peliculaId, cid);
        }
    }

    default void deleteAll(UUID peliculaId, Set<UUID> categoriaIds) {
        for (UUID cid : categoriaIds) {
            delete(peliculaId, cid);
        }
    }
}
