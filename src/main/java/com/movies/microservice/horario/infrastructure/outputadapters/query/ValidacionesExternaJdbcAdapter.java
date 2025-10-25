package com.movies.microservice.horario.infrastructure.outputadapters.query;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.movies.microservice.horario.application.outputports.query.ValidacionesExternaOutputPort;

import lombok.RequiredArgsConstructor;

@Component
@ConditionalOnProperty(prefix = "horario.validation", name = "external", havingValue = "jdbc")
@RequiredArgsConstructor
public class ValidacionesExternaJdbcAdapter implements ValidacionesExternaOutputPort {

    private final JdbcTemplate jdbc;

    private static byte[] uuidToBytes(UUID u) {
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(u.getMostSignificantBits());
        bb.putLong(u.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public boolean existePelicula(UUID peliculaId) {
        Integer n = jdbc.queryForObject(
                "SELECT COUNT(*) FROM movie WHERE id = ?",
                new Object[]{ uuidToBytes(peliculaId) },
                Integer.class
        );
        return n != null && n > 0;
    }

    @Override
    public boolean existeCinema(UUID cinemaId) {
        // ⚠️ Usa esquema calificado si está en otra BD del mismo MySQL
        Integer n = jdbc.queryForObject(
                "SELECT COUNT(*) FROM cinema_db.cinema WHERE id = ?",
                new Object[]{ uuidToBytes(cinemaId) },
                Integer.class
        );
        return n != null && n > 0;
    }

    @Override
    public boolean existeSala(UUID salaId) {
        Integer n = jdbc.queryForObject(
                "SELECT COUNT(*) FROM cinema_db.sala WHERE id = ?",
                new Object[]{ uuidToBytes(salaId) },
                Integer.class
        );
        return n != null && n > 0;
    }
}