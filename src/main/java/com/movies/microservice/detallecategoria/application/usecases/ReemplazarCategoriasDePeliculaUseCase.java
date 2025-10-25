package com.movies.microservice.detallecategoria.application.usecases;

import com.movies.microservice.detallecategoria.application.inputports.ReemplazarCategoriasDePeliculaInputPort;
import com.movies.microservice.detallecategoria.application.outputports.messaging.DetalleCategoriaEventPublisherOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.persistence.DetalleCategoriaRepositorioOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.query.CategoriaQueryOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.query.PeliculaExistenciaOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReemplazarCategoriasDePeliculaUseCase implements ReemplazarCategoriasDePeliculaInputPort {

    private final DetalleCategoriaRepositorioOutputPort repo;
    private final PeliculaExistenciaOutputPort peliculaQuery;
    private final CategoriaQueryOutputPort categoriaQuery;
    private final DetalleCategoriaEventPublisherOutputPort publisher;

    @Override
    public void reemplazar(java.util.UUID peliculaId, java.util.Set<java.util.UUID> nuevasCategorias) {
        if (peliculaId == null) {
            throw new IllegalArgumentException("peliculaId obligatorio");
        }
        if (!peliculaQuery.existsById(peliculaId)) {
            throw new IllegalArgumentException("Película no existe");
        }

        Set<java.util.UUID> target = nuevasCategorias != null
                ? nuevasCategorias.stream().filter(Objects::nonNull).collect(Collectors.toSet())
                : Collections.emptySet();

        // Validar cada categoría: existencia y activa
        for (UUID cid : target) {
            if (!categoriaQuery.existsById(cid)) {
                throw new IllegalArgumentException("Categoría no existe: " + cid);
            }
            if (!categoriaQuery.isActive(cid)) {
                throw new IllegalArgumentException("Categoría inactiva: " + cid);
            }
        }

        // Estado actual
        Set<UUID> actuales = new HashSet<>(repo.findCategoriasByPelicula(peliculaId));

        // Diff
        Set<UUID> aAgregar = new HashSet<>(target);
        aAgregar.removeAll(actuales);
        Set<UUID> aQuitar = new HashSet<>(actuales);
        aQuitar.removeAll(target);

        // Persistencia
        repo.saveAll(peliculaId, aAgregar);
        repo.deleteAll(peliculaId, aQuitar);

        // Eventos
        for (UUID cid : aAgregar) {
            publisher.publishCategoriaAsignada(peliculaId, cid);
        }
        for (UUID cid : aQuitar) {
            publisher.publishCategoriaRemovida(peliculaId, cid);
        }
    }
}
