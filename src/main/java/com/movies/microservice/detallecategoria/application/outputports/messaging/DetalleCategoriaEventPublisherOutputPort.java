package com.movies.microservice.detallecategoria.application.outputports.messaging;

import java.util.UUID;

public interface DetalleCategoriaEventPublisherOutputPort {

    void publishCategoriaAsignada(UUID peliculaId, UUID categoriaId);

    void publishCategoriaRemovida(UUID peliculaId, UUID categoriaId);
}
