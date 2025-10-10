package com.movies.microservice.poster.infrastructure.outputadapters.messaging;

import com.movies.microservice.poster.application.outputports.messaging.PosterEventPublisherOutputPort;
import com.movies.microservice.poster.domain.events.*;
import org.springframework.stereotype.Component;

@Component
public class PosterEventPublisherNoOp implements PosterEventPublisherOutputPort {

    @Override
    public void publish(PosterCreadoEvent e) {
    }

    @Override
    public void publish(PosterActivadoEvent e) {
    }

    @Override
    public void publish(PosterDesactivadoEvent e) {
    }
}
