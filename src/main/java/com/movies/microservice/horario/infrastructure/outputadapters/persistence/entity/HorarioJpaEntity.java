package com.movies.microservice.horario.infrastructure.outputadapters.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "horario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HorarioJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "movie_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID peliculaId;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "cinema_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID cinemaId;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "sala_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID salaId;

    @Column(nullable = false, length = 20)
    private String idioma;   // ES, EN, SUB, DOB

    @Column(nullable = false, length = 20)
    private String formato;  // 2D, 3D, IMAX

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fin;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "is_active", nullable = false)
    private boolean activo = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}