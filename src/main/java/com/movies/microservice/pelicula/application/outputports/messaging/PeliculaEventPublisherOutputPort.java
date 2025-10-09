package com.movies.microservice.pelicula.application.outputports.messaging;

import com.movies.microservice.pelicula.domain.events.*;

public interface PeliculaEventPublisherOutputPort {

    void publish(PeliculaCreadaEvent event);

    void publish(PeliculaActualizadaEvent event);

    void publish(PeliculaActivadaEvent event);

    void publish(PeliculaDesactivadaEvent event);
}
