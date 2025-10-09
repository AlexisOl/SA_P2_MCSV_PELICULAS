package com.movies.microservice.pelicula.domain.valueobjects;

import java.util.UUID;

public class PeliculaId {

    private final UUID value;

    public PeliculaId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PeliculaId other = (PeliculaId) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
