package com.movies.microservice.pelicula.infrastructure.config;

import com.movies.microservice.pelicula.application.inputports.*;
import com.movies.microservice.pelicula.application.outputports.messaging.PeliculaEventPublisherOutputPort;
import com.movies.microservice.pelicula.application.outputports.persistence.PeliculaRepositorioOutputPort;
import com.movies.microservice.pelicula.application.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class PeliculaUseCasesConfig {

    private final PeliculaRepositorioOutputPort repo;
    private final PeliculaEventPublisherOutputPort publisher;

    @Bean
    public CrearPeliculaInputPort crearPeliculaUC() {
        return new CrearPeliculaUseCase(repo, publisher);
    }

    @Bean
    public ActualizarPeliculaInputPort actualizarPeliculaUC() {
        return new ActualizarPeliculaUseCase(repo, publisher);
    }

    @Bean
    public ActivarPeliculaInputPort activarPeliculaUC() {
        return new ActivarPeliculaUseCase(repo, publisher);
    }

    @Bean
    public DesactivarPeliculaInputPort desactivarPeliculaUC() {
        return new DesactivarPeliculaUseCase(repo, publisher);
    }

    @Bean
    public ListarPeliculasInputPort listarPeliculasUC() {
        return new ListarPeliculasUseCase(repo);
    }
}
