package com.movies.microservice.pelicula.application.usecases;

import com.movies.microservice.pelicula.application.commands.ActualizarPeliculaCommand;
import com.movies.microservice.pelicula.application.inputports.ActualizarPeliculaInputPort;
import com.movies.microservice.pelicula.application.outputports.messaging.PeliculaEventPublisherOutputPort;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.domain.events.PeliculaActualizadaEvent;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ActualizarPeliculaUseCase implements ActualizarPeliculaInputPort {

    private final @NonNull
    PeliculaRepositorioOutputPort repo;
    private final @NonNull
    PeliculaEventPublisherOutputPort publisher;
    
    @Transactional
    @Override
    public Pelicula actualizar(ActualizarPeliculaCommand cmd) {
        Optional<Pelicula> opt = repo.findById(new PeliculaId(cmd.getPeliculaId()));
        Pelicula actual = opt.orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada"));

        // Aplica solo cambios no nulos
        String titulo = cmd.getTitulo() != null ? cmd.getTitulo() : actual.getTitulo();
        String sinopsis = cmd.getSinopsis() != null ? cmd.getSinopsis() : actual.getSinopsis();
        int duracion = cmd.getDuracion() != null ? cmd.getDuracion() : actual.getDuracion();
        var posters = cmd.getPosters() != null ? cmd.getPosters() : actual.getPosters();
        var cast = cmd.getCast() != null ? cmd.getCast() : actual.getCast();
        String director = cmd.getDirector() != null ? cmd.getDirector() : actual.getDirector();
        var clasificacion = cmd.getClasificacion() != null ? cmd.getClasificacion() : actual.getClasificacion();
        var fechaEstreno = cmd.getFechaEstreno() != null ? cmd.getFechaEstreno() : actual.getFechaEstreno();

        actual.actualizar(titulo, sinopsis, duracion, posters, cast, director, clasificacion, fechaEstreno);

        // Si decide permitir activar/desactivar desde este comando:
        if (cmd.getActiva() != null) {
            if (cmd.getActiva()) {
                actual.activar();
            } else {
                actual.desactivar();
            }
        }

        Pelicula updated = repo.update(actual);
        publisher.publish(new PeliculaActualizadaEvent(updated.getId()));
        return updated;
    }
}
