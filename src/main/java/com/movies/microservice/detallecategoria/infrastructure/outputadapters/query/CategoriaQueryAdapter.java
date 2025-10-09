package com.movies.microservice.detallecategoria.infrastructure.outputadapters.query;

import com.movies.microservice.detallecategoria.application.outputports.query.CategoriaQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.springframework.jdbc.core.ResultSetExtractor;

@Component
@RequiredArgsConstructor
public class CategoriaQueryAdapter implements CategoriaQueryOutputPort {

  private final JdbcTemplate jdbc;

  private static byte[] uuidToBytes(UUID u) {
    ByteBuffer bb = ByteBuffer.allocate(16);
    bb.putLong(u.getMostSignificantBits());
    bb.putLong(u.getLeastSignificantBits());
    return bb.array();
  }
  private static UUID bytesToUuid(byte[] b) {
    ByteBuffer bb = ByteBuffer.wrap(b);
    return new UUID(bb.getLong(), bb.getLong());
  }

  @Override
  public boolean existsById(UUID categoriaId) {
    Integer n = jdbc.queryForObject(
        "SELECT COUNT(*) FROM category WHERE id = ?",
        new Object[]{ uuidToBytes(categoriaId) },
        Integer.class
    );
    return n != null && n > 0;
  }

  @Override
  public boolean isActive(UUID categoriaId) {
    Integer n = jdbc.queryForObject(
        "SELECT COUNT(*) FROM category WHERE id = ? AND is_active = 1",
        new Object[]{ uuidToBytes(categoriaId) },
        Integer.class
    );
    return n != null && n > 0;
  }

  @Override
public Map<UUID, String> findNamesByIds(Set<UUID> ids) {
  if (ids == null || ids.isEmpty()) return Collections.emptyMap();

  String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));
  String sql = "SELECT id, name FROM category WHERE id IN (" + placeholders + ")";
  Object[] params = ids.stream().map(CategoriaQueryAdapter::uuidToBytes).toArray();

  return jdbc.query(sql,
      (ResultSetExtractor<Map<UUID, String>>) rs -> {
        Map<UUID, String> map = new HashMap<>();
        while (rs.next()) {
          UUID id = bytesToUuid(rs.getBytes("id"));
          map.put(id, rs.getString("name"));
        }
        return map;
      },
      params
  );
}

  private static Map<UUID, String> readIdNameMap(ResultSet rs) throws SQLException {
    Map<UUID, String> map = new HashMap<>();
    while (rs.next()) {
      UUID id = bytesToUuid(rs.getBytes("id"));
      map.put(id, rs.getString("name"));
    }
    return map;
  }
}