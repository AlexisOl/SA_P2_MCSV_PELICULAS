package com.movies.microservice.horario.infrastructure.eventos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.microservice.horario.application.outputports.eventos.NotificarHorarioOutputPort;

import com.movies.microservice.horario.domain.entities.Horario;
import com.movies.microservice.horario.infrastructure.eventos.dto.NotificarHorarioDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificarHorarioAdaptador implements NotificarHorarioOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public NotificarHorarioAdaptador(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void notificarHorarioCreado(Horario horario, Integer fila, Integer columna) {
        try {
            NotificarHorarioDto notificacion = new NotificarHorarioDto(
                    horario.getId(),
                    horario.getSalaId(),
                    fila,
                    columna
            );
            String mensaje = objectMapper.writeValueAsString(notificacion);
            kafkaTemplate.send("horario-creada", mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar notificación de horario creada: " + e.getMessage());
        }
    }
}
