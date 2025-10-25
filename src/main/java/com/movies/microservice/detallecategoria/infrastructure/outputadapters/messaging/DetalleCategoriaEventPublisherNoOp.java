package com.movies.microservice.detallecategoria.infrastructure.outputadapters.messaging;

import com.movies.microservice.detallecategoria.application.outputports.messaging.DetalleCategoriaEventPublisherOutputPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DetalleCategoriaEventPublisherNoOp implements DetalleCategoriaEventPublisherOutputPort {

    @Override
    public void publishCategoriaAsignada(UUID peliculaId, UUID categoriaId) {
    }

    @Override
    public void publishCategoriaRemovida(UUID peliculaId, UUID categoriaId) {
    }
}
