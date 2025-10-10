package com.movies.microservice.pelicula.infrastructure.outputadapters.query;

import com.movies.microservice.pelicula.application.outputports.query.PosterQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PosterQueryAdapter implements PosterQueryOutputPort {

    private final JdbcTemplate jdbc;

    private static byte[] uuidToBytes(UUID u) {
        var bb = ByteBuffer.allocate(16);
        bb.putLong(u.getMostSignificantBits());
        bb.putLong(u.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public List<String> findUrlsByPelicula(UUID peliculaId) {
        String sql = """
            SELECT url
            FROM poster
            WHERE movie_id = ? AND is_active = 1
            ORDER BY COALESCE(orden,0), created_at
        """;
        return jdbc.query(sql, (rs, i) -> rs.getString("url"), uuidToBytes(peliculaId));
    }
}