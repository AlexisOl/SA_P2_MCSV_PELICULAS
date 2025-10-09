package com.movies.microservice.detallecategoria.infrastructure.outputadapters.query;

import com.movies.microservice.detallecategoria.application.outputports.query.PeliculaExistenciaOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.nio.ByteBuffer;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PeliculaExistenciaAdapter implements PeliculaExistenciaOutputPort {

  private final JdbcTemplate jdbc;

  private static byte[] uuidToBytes(UUID u) {
    ByteBuffer bb = ByteBuffer.allocate(16);
    bb.putLong(u.getMostSignificantBits());
    bb.putLong(u.getLeastSignificantBits());
    return bb.array();
  }

  @Override
  public boolean existsById(UUID peliculaId) {
    Integer n = jdbc.queryForObject(
        "SELECT COUNT(*) FROM movie WHERE id = ?",
        new Object[]{ uuidToBytes(peliculaId) },
        Integer.class
    );
    return n != null && n > 0;
  }
}
