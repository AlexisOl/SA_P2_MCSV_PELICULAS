
package com.movies.microservice.poster.application.outputports.messaging;

import com.movies.microservice.poster.domain.events.*;

public interface PosterEventPublisherOutputPort {

    void publish(PosterCreadoEvent e);

    void publish(PosterActivadoEvent e);

    void publish(PosterDesactivadoEvent e);
}
