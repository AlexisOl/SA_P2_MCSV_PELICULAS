package com.movies.microservice.pelicula.infrastructure.outputadapters.query;

import com.movies.microservice.detallecategoria.application.outputports.query.PeliculaQueryOutputPort;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class PeliculaQueryAdapter implements PeliculaQueryOutputPort {
  private final JdbcTemplate jdbc;

  private byte[] uuidToBytes(UUID u) {
    var bb = java.nio.ByteBuffer.allocate(16);
    bb.putLong(u.getMostSignificantBits());
    bb.putLong(u.getLeastSignificantBits());
    return bb.array();
  }

  @Override
  public Map<UUID,String> findTitlesByIds(Set<UUID> ids) {
    if (ids == null || ids.isEmpty()) return Map.of();
    String placeholders = ids.stream().map(x -> "?").collect(java.util.stream.Collectors.joining(","));
    String sql = "SELECT id, title FROM movie WHERE id IN ("+placeholders+")";
    Object[] params = ids.stream().map(this::uuidToBytes).toArray();
    return jdbc.query(sql, rs -> {
      Map<UUID,String> map = new java.util.HashMap<>();
      while (rs.next()) {
        var bb = java.nio.ByteBuffer.wrap(rs.getBytes("id"));
        UUID id = new UUID(bb.getLong(), bb.getLong());
        map.put(id, rs.getString("title"));
      }
      return map;
    }, params);
  }
}