package com.movies.microservice.pelicula.infrastructure.outputadapters.messaging;

import com.movies.microservice.pelicula.application.outputports.messaging.PeliculaEventPublisherOutputPort;
import com.movies.microservice.pelicula.domain.events.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // <— sólo en dev
// quitar esto
public class PeliculaEventPublisherNoOp implements PeliculaEventPublisherOutputPort {

    @Override
    public void publish(PeliculaCreadaEvent e) {
    }

    @Override
    public void publish(PeliculaActualizadaEvent e) {
    }

    @Override
    public void publish(PeliculaActivadaEvent e) {
    }

    @Override
    public void publish(PeliculaDesactivadaEvent e) {
    }
}
