package com.movies.microservice.categoria.application.outputports.messaging;

import com.movies.microservice.categoria.domain.events.*;

public interface CategoriaEventPublisherOutputPort {

    void publish(CategoriaCreadaEvent e);

    void publish(CategoriaActualizadaEvent e);

    void publish(CategoriaActivadaEvent e);

    void publish(CategoriaDesactivadaEvent e);
}
