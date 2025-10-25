package com.movies.microservice.detallecategoria.infrastructure.outputadapters.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movie_category",
        uniqueConstraints = @UniqueConstraint(name = "ux_movie_category", columnNames = {"movie_id", "category_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieCategoryJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "movie_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID movieId;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "category_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
