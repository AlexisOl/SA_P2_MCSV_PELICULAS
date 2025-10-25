package com.movies.microservice.pelicula.infrastructure.outputadapters.messaging;

import com.movies.microservice.pelicula.application.outputports.messaging.PeliculaEventPublisherOutputPort;
import com.movies.microservice.pelicula.domain.events.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import org.springframework.context.annotation.Profile;


//@Profile("prod")
@Component
@RequiredArgsConstructor
public class PeliculaEventPublisherAdapter implements PeliculaEventPublisherOutputPort {

    private static final String TOPIC = "movies.events.v1";
    private final KafkaTemplate<String, Object> kafka;

    @Override
    public void publish(PeliculaCreadaEvent e) {
        send("PeliculaCreada", Map.of("peliculaId", e.getPeliculaId().getValue()));
    }

    @Override
    public void publish(PeliculaActualizadaEvent e) {
        send("PeliculaActualizada", Map.of("peliculaId", e.getPeliculaId().getValue()));
    }

    @Override
    public void publish(PeliculaActivadaEvent e) {
        send("PeliculaActivada", Map.of("peliculaId", e.getPeliculaId().getValue()));
    }

    @Override
    public void publish(PeliculaDesactivadaEvent e) {
        send("PeliculaDesactivada", Map.of("peliculaId", e.getPeliculaId().getValue()));
    }

    private void send(String type, Map<String, Object> payload) {
        var event = Map.of(
                "event", type,
                "version", 1,
                "occurredAt", java.time.Instant.now().toString(),
                "payload", payload
        );
        kafka.send(TOPIC, payload.get("peliculaId").toString(), event);
    }
}
