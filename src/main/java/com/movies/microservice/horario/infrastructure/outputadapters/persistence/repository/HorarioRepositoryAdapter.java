package com.movies.microservice.horario.infrastructure.outputadapters.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.movies.microservice.horario.application.outputports.persistence.HorarioRepositorioOutputPort;
import com.movies.microservice.horario.domain.entities.Horario;
import com.movies.microservice.horario.infrastructure.outputadapters.persistence.entity.HorarioJpaEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HorarioRepositoryAdapter implements HorarioRepositorioOutputPort {

    private final JpaHorarioRepository jpa;

    @Override
    public Horario save(Horario h) {
        var e = toEntity(h);
        var saved = jpa.save(e);
        return toDomain(saved);
    }

    @Override
    public Optional<Horario> findById(UUID id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public void delete(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Horario> search(UUID peliculaId, UUID cinemaId,
                                java.time.LocalDateTime desde, java.time.LocalDateTime hasta,
                                boolean soloActivos) {
        return jpa.search(peliculaId, cinemaId, desde, hasta, soloActivos)
                  .stream().map(this::toDomain).toList();
    }

    @Override
    public long countTraslapes(UUID salaId, java.time.LocalDateTime inicio, java.time.LocalDateTime fin) {
        return jpa.countTraslapes(salaId, inicio, fin);
    }

    // --- mapping
    private HorarioJpaEntity toEntity(Horario d) {
        return HorarioJpaEntity.builder()
                .id(d.getId())
                .peliculaId(d.getPeliculaId())
                .cinemaId(d.getCinemaId())
                .salaId(d.getSalaId())
                .idioma(d.getIdioma())
                .formato(d.getFormato())
                .inicio(d.getInicio())
                .fin(d.getFin())
                .precio(d.getPrecio())
                .activo(d.isActivo())
                .createdAt(d.getCreatedAt())
                .updatedAt(d.getUpdatedAt())
                .build();
    }

    private Horario toDomain(HorarioJpaEntity e) {
        return Horario.builder()
                .id(e.getId())
                .peliculaId(e.getPeliculaId())
                .cinemaId(e.getCinemaId())
                .salaId(e.getSalaId())
                .idioma(e.getIdioma())
                .formato(e.getFormato())
                .inicio(e.getInicio())
                .fin(e.getFin())
                .precio(e.getPrecio())
                .activo(e.isActivo())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}