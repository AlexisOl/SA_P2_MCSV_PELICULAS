package com.movies.microservice.categoria.infrastructure.outputadapters.persistence.messaging;

import com.movies.microservice.categoria.application.outputports.messaging.CategoriaEventPublisherOutputPort;
import com.movies.microservice.categoria.domain.events.*;
import org.springframework.stereotype.Component;

@Component
public class CategoriaEventPublisherNoOp implements CategoriaEventPublisherOutputPort {

    @Override
    public void publish(CategoriaCreadaEvent e) {
    }

    @Override
    public void publish(CategoriaActualizadaEvent e) {
    }

    @Override
    public void publish(CategoriaActivadaEvent e) {
    }

    @Override
    public void publish(CategoriaDesactivadaEvent e) {
    }
}
