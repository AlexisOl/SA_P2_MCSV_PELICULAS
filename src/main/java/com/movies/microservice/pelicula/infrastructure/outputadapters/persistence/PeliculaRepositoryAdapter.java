package com.movies.microservice.pelicula.infrastructure.outputadapters.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.domain.valueobjects.Clasificacion;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PeliculaRepositoryAdapter implements PeliculaRepositorioOutputPort {

    private final JpaPeliculaRepository jpa;
    private final ObjectMapper mapper;

    @Override
    public Pelicula save(Pelicula pelicula) {
        PeliculaJpaEntity e = toEntity(pelicula);
        if (e.getId() == null) {
            e.setId(UUID.randomUUID());
        }
        PeliculaJpaEntity saved = jpa.save(e);
        return toDomain(saved);
    }

    @Override
    public Pelicula update(Pelicula pelicula) {
        PeliculaJpaEntity saved = jpa.save(toEntity(pelicula));
        return toDomain(saved);
    }

    @Override
    public Optional<Pelicula> findById(PeliculaId id) {
        return jpa.findById(id.getValue()).map(this::toDomain);
    }

    @Override
    public boolean existsByTituloAndReleaseDate(String titulo, LocalDate releaseDate) {
        return jpa.existsTituloAndReleaseDate(titulo, releaseDate);
    }

    @Override
    public List<Pelicula> search(String texto, String clasificacion, Boolean activa,
            LocalDate releasedFrom, LocalDate releasedTo,
            List<String> categorias, int page, int size, String sort) {
        // TODO: integrar 'categorias' desde hexágono Categoría/consulta
        var list = jpa.search(texto, clasificacion, activa, releasedFrom, releasedTo);
        return list.stream().map(this::toDomain).toList();
    }

    // ===== mapping =====
    private PeliculaJpaEntity toEntity(Pelicula p) {
        return PeliculaJpaEntity.builder()
                .id(p.getId() != null ? p.getId().getValue() : null)
                .titulo(p.getTitulo())
                .sinopsis(p.getSinopsis())
                .duracion(p.getDuracion())
                .postersJson(writeJson(p.getPosters()))
                .castJson(writeJson(p.getCast()))
                .director(p.getDirector())
                .clasificacion(p.getClasificacion() != null ? p.getClasificacion().name() : null)
                .activa(p.isActiva())
                .fechaEstreno(p.getFechaEstreno())
                .build();
    }

    private Pelicula toDomain(PeliculaJpaEntity e) {
        return new Pelicula(
                new com.movies.microservice.pelicula.domain.valueobjects.PeliculaId(e.getId()),
                e.getTitulo(),
                e.getSinopsis(),
                e.getDuracion(),
                readList(e.getPostersJson()),
                readList(e.getCastJson()),
                e.getDirector(),
                e.getClasificacion() != null ? Clasificacion.valueOf(e.getClasificacion()) : null,
                e.isActiva(),
                e.getFechaEstreno()
        );
    }

    private String writeJson(List<String> list) {
        try {
            return list != null ? mapper.writeValueAsString(list) : null;
        } catch (Exception ex) {
            throw new RuntimeException("Error serializando JSON", ex);
        }
    }

    private List<String> readList(String json) {
        try {
            return json != null ? mapper.readValue(json, new TypeReference<List<String>>() {
            }) : Collections.emptyList();
        } catch (Exception ex) {
            throw new RuntimeException("Error deserializando JSON", ex);
        }
    }
}
