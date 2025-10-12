package com.movies.microservice.horario.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Horario {
    private UUID id;
    private UUID peliculaId;
    private UUID cinemaId;   // viene del MS de cines
    private UUID salaId;     // viene del MS de cines
    private String idioma;   // ES, EN, SUB, DOB
    private String formato;  // 2D, 3D, IMAX
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private BigDecimal precio;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Reglas de dominio
    public void validar() {
        if (peliculaId == null) throw new IllegalArgumentException("peliculaId es obligatorio");
        if (cinemaId == null)   throw new IllegalArgumentException("cinemaId es obligatorio");
        if (salaId == null)     throw new IllegalArgumentException("salaId es obligatorio");
        if (idioma == null || idioma.isBlank())   throw new IllegalArgumentException("idioma es obligatorio");
        if (formato == null || formato.isBlank()) throw new IllegalArgumentException("formato es obligatorio");
        if (inicio == null || fin == null)        throw new IllegalArgumentException("inicio/fin son obligatorios");
        if (!fin.isAfter(inicio))                 throw new IllegalArgumentException("fin debe ser posterior a inicio");
        if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("precio debe ser >= 0");
        }
    }

    public void desactivar() { this.activo = false; }
    public void activar()    { this.activo = true;  }
}