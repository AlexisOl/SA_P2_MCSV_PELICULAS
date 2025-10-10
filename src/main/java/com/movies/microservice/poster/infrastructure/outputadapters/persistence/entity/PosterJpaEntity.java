package com.movies.microservice.poster.infrastructure.outputadapters.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "poster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PosterJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "movie_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID peliculaId;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "is_active", nullable = false)
    private boolean activo = true;

    @Column(name = "orden")
    private Integer orden = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
