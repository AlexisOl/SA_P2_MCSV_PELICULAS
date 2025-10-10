package com.movies.microservice.poster.domain.entities;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poster {

    private UUID id;
    private UUID peliculaId;
    private String url;
    private boolean activo;
    private Integer orden;                 // 0 = principal (por defecto)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --- Reglas de negocio ---
    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void cambiarOrden(int nuevo) {
        if (nuevo < 0) {
            throw new IllegalArgumentException("El orden no puede ser negativo");
        }
        this.orden = nuevo;
    }

    public void validar() {
        if (peliculaId == null) {
            throw new IllegalArgumentException("peliculaId es obligatorio");
        }
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("url es obligatoria");
        }
        String u = url.trim().toLowerCase();
        if (!(u.startsWith("http://") || u.startsWith("https://"))) {
            throw new IllegalArgumentException("url debe ser http(s)");
        }
    }
}
