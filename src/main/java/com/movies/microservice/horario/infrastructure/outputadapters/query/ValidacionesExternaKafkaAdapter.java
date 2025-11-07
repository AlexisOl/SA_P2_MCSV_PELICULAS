package com.movies.microservice.horario.infrastructure.outputadapters.query;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.microservice.horario.application.outputports.query.ValidacionesExternaOutputPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "horario.validation", name = "external", havingValue = "kafka")
public class ValidacionesExternaKafkaAdapter implements ValidacionesExternaOutputPort {

    private final KafkaTemplate<String, String> kafka;
    private final ObjectMapper mapper;

    private final ConcurrentMap<String, CompletableFuture<Boolean>> pendingCine = new ConcurrentHashMap<>();

    // === Solo cine por ahora ===
    @Override
    public boolean existeCinema(UUID cinemaId) {
        try {
            String correlationId = UUID.randomUUID().toString();
            var dto = new VerificarCineDTO(cinemaId);
            String payload = mapper.writeValueAsString(dto);

            CompletableFuture<Boolean> future = new CompletableFuture<>();
            pendingCine.put(correlationId, future);
            System.out.println("Kafaka events----------------");

            Message<String> message = MessageBuilder
                    .withPayload(payload)
                    .setHeader(KafkaHeaders.TOPIC, "verificar-cine")
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();

            kafka.send(message);

            // Esperar respuesta (máx 5 segundos)
            return future.orTimeout(5, TimeUnit.SECONDS).join();
        } catch (Exception e) {
            throw new IllegalStateException("Error verificando cine: " + e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "respuesta-verificar-cine", groupId = "movies-horario-group")
    public void onCineReply(String payload,
                            @org.springframework.messaging.handler.annotation.Header(KafkaHeaders.CORRELATION_ID) String correlationId)
            throws Exception {
        var resp = mapper.readValue(payload, VerificarCineRespuestaDTO.class);
        var cf = pendingCine.remove(correlationId);
        if (cf != null) cf.complete(resp.existe());
    }

    // === Aún no implementados ===
    @Override public boolean existePelicula(UUID peliculaId) { return true; }
    @Override public boolean existeSala(UUID salaId) { return true; }

    // === DTOs locales (puedes moverlos a un módulo común después) ===
    public record VerificarCineDTO(UUID idCine) {}
    public record VerificarCineRespuestaDTO(boolean existe, String correlationId) {}
}