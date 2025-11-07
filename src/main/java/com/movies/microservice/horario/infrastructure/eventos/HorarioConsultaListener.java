package com.movies.microservice.horario.infrastructure.eventos;

import com.example.comun.DTO.boletos.HorarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.microservice.horario.application.outputports.persistence.HorarioRepositorioOutputPort;
import com.movies.microservice.horario.domain.entities.Horario;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HorarioConsultaListener {

    private final HorarioRepositorioOutputPort horarioRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "consulta-horario", groupId = "movies-group")
    public void handleConsultaHorario(@Payload String mensaje,
                                      @Header(KafkaHeaders.CORRELATION_ID) String correlationId) {
        try {
            System.out.println(" Consulta horario recibida - CorrelationId: " + correlationId);

            // Deserializar la consulta
            ConsultaHorarioDTO consulta = objectMapper.readValue(mensaje, ConsultaHorarioDTO.class);

            // Buscar el horario en el repositorio
            Horario horario = horarioRepo.findById(consulta.funcionId())
                    .orElse(null);

            // Preparar respuesta
            HorarioDTO respuesta;
            if (horario != null) {
                respuesta = new HorarioDTO(
                        horario.getId(),
                        horario.getPeliculaId(),
                        horario.getInicio(),
                        horario.getFin(),
                        horario.getIdioma(),
                        horario.getFormato(),
                        correlationId
                );
                System.out.println(" Horario encontrado: " + horario.getId());
            } else {
                // Horario no encontrado
                respuesta = new HorarioDTO(
                        null,
                        null,
                        null,
                        null,
                        "N/A",
                        "N/A",
                        correlationId
                );
                System.out.println("️ Horario no encontrado: " + consulta.funcionId());
            }

            // Serializar y enviar respuesta
            String respuestaMensaje = objectMapper.writeValueAsString(respuesta);

            Message<String> message = MessageBuilder
                    .withPayload(respuestaMensaje)
                    .setHeader(KafkaHeaders.TOPIC, "respuesta-consulta-horario")
                    .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                    .build();

            kafkaTemplate.send(message);
            System.out.println(" Respuesta horario enviada - CorrelationId: " + correlationId);

        } catch (Exception e) {
            System.err.println(" Error procesando consulta horario: " + e.getMessage());
            e.printStackTrace();

            // Enviar respuesta de error
            try {
                HorarioDTO errorResponse = new HorarioDTO(
                        null, null, null, null,
                        "ERROR", "ERROR", correlationId
                );

                String errorMensaje = objectMapper.writeValueAsString(errorResponse);
                Message<String> errorMessage = MessageBuilder
                        .withPayload(errorMensaje)
                        .setHeader(KafkaHeaders.TOPIC, "respuesta-consulta-horario")
                        .setHeader(KafkaHeaders.CORRELATION_ID, correlationId)
                        .build();

                kafkaTemplate.send(errorMessage);
            } catch (Exception ex) {
                System.err.println(" Error enviando respuesta de error: " + ex.getMessage());
            }
        }
    }

    // DTO para la consulta entrante
    private record ConsultaHorarioDTO(UUID funcionId, String correlationId) {}

}
