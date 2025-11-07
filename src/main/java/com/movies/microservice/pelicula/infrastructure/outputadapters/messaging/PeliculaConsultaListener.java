package com.movies.microservice.pelicula.infrastructure.outputadapters.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PeliculaConsultaListener {

    private final PeliculaRepositorioOutputPort peliculaRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "consulta-pelicula", groupId = "movies-group")
    public void handleConsultaPelicula(@Payload String mensaje,
                                       @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {
        try {
            var consulta = objectMapper.readValue(mensaje, ConsultaDTO.class);

            var pelicula = peliculaRepo.findById(new PeliculaId(consulta.peliculaId()))
                    .orElse(null);

            var respuesta = new PeliculaDTO(
                    pelicula != null ? pelicula.getId().getValue() : null,
                    pelicula != null ? pelicula.getTitulo() : "No encontrada",
                    pelicula != null ? pelicula.getDuracion() : 0,
                    correlationId
            );

            String respuestaMensaje = objectMapper.writeValueAsString(respuesta);
            Message<String> message = MessageBuilder
                    .withPayload(respuestaMensaje)
                    .setHeader(KafkaHeaders.TOPIC, "respuesta-consulta-pelicula")
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();

            kafkaTemplate.send(message);
        } catch (Exception e) {
            System.err.println("Error procesando consulta película: " + e.getMessage());
        }
    }

    private record ConsultaDTO(UUID peliculaId, String correlationId) {}
    private record PeliculaDTO(UUID peliculaId, String titulo, Integer duracion, String correlationId) {}

}
