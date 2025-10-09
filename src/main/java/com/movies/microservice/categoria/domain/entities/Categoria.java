package com.movies.microservice.categoria.domain.entities;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    private UUID id;
    private String nombre;
    private boolean activa;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
    public void activar() {
        this.activa = true;
    }

    public void desactivar() {
        this.activa = false;
    }

    public void editarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        this.nombre = nuevoNombre.trim();
    }
}
