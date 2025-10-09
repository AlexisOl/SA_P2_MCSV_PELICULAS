package com.movies.microservice.pelicula.infrastructure.outputadapters.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.BINARY)                 // almacena UUID como BINARY(16)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "title", nullable = false, length = 200)
    private String titulo;

    @Lob
    @Column(name = "synopsis")
    private String sinopsis;

    @Column(name = "duration_min", nullable = false)
    private int duracion;

    @Lob
    @Column(name = "posters_json")
    private String postersJson;                    // JSON String de List<String>

    @Lob
    @Column(name = "cast_json")
    private String castJson;                       // JSON String de List<String>

    @Column(name = "director", length = 120)
    private String director;

    @Column(name = "classification", nullable = false, length = 8)
    private String clasificacion;                  // A, B, B12, B15, C

    @Column(name = "is_active", nullable = false)
    private boolean activa;

    @Column(name = "release_date")
    private LocalDate fechaEstreno;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
