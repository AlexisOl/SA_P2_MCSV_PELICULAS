package com.movies.microservice.pelicula.application.usecases;

import com.movies.microservice.pelicula.application.commands.CrearPeliculaCommand;
import com.movies.microservice.pelicula.application.inputports.CrearPeliculaInputPort;
import com.movies.microservice.pelicula.application.outputports.messaging.PeliculaEventPublisherOutputPort;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.domain.events.PeliculaCreadaEvent;
import com.movies.microservice.pelicula.domain.valueobjects.PeliculaId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrearPeliculaUseCase implements CrearPeliculaInputPort {

    private final @NonNull
    PeliculaRepositorioOutputPort repo;
    private final @NonNull
    PeliculaEventPublisherOutputPort publisher;

    @Override
    public Pelicula crear(CrearPeliculaCommand cmd) {
        if (cmd.getDuracion() <= 0) {
            throw new IllegalArgumentException("Duración inválida");
        }

        // Regla simple de unicidad (opcional): título+fechaEstreno
        if (cmd.getFechaEstreno() != null
                && repo.existsByTituloAndReleaseDate(cmd.getTitulo(), cmd.getFechaEstreno())) {
            throw new IllegalArgumentException("Ya existe una película con ese título y fecha de estreno");
        }

        Pelicula pelicula = new Pelicula(
                null, // ✅ el constructor genera un UUID interno
                cmd.getTitulo(), cmd.getSinopsis(), cmd.getDuracion(),
                cmd.getPosters(), cmd.getCast(), cmd.getDirector(),
                cmd.getClasificacion(), cmd.isActiva(), cmd.getFechaEstreno()
        );

        Pelicula saved = repo.save(pelicula);

        // Emitir evento
        publisher.publish(new PeliculaCreadaEvent(saved.getId()));
        return saved;
    }
}
