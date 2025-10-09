package com.movies.microservice.pelicula.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.movies.microservice.pelicula.domain.valueobjects.Clasificacion;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;

public class Pelicula {

    private final PeliculaId id;
    private String titulo;
    private String sinopsis;
    private int duracion; // minutos
    private List<String> posters;
    private List<String> cast;
    private String director;
    private Clasificacion clasificacion;
    private boolean activa;
    private LocalDate fechaEstreno;

    
    public Pelicula(PeliculaId id, String titulo, String sinopsis, int duracion,
            List<String> posters, List<String> cast, String director,
            Clasificacion clasificacion, boolean activa, LocalDate fechaEstreno) {

        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0");
        }
        this.id = id != null ? id : new PeliculaId(UUID.randomUUID());
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.posters = posters;
        this.cast = cast;
        this.director = director;
        this.clasificacion = clasificacion;
        this.activa = activa;
        this.fechaEstreno = fechaEstreno;
    }

    
    public void activar() {
        this.activa = true;
    }

    public void desactivar() {
        this.activa = false;
    }

    public void actualizar(String titulo, String sinopsis, int duracion,
            List<String> posters, List<String> cast, String director,
            Clasificacion clasificacion, LocalDate fechaEstreno) {

        if (duracion <= 0) {
            throw new IllegalArgumentException("Duración inválida");
        }
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.posters = posters;
        this.cast = cast;
        this.director = director;
        this.clasificacion = clasificacion;
        this.fechaEstreno = fechaEstreno;
    }

    // Getters
    public PeliculaId getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public int getDuracion() {
        return duracion;
    }

    public List<String> getPosters() {
        return posters;
    }

    public List<String> getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }
}
