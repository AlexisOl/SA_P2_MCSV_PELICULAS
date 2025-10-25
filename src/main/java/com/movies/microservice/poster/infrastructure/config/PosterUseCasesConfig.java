
package com.movies.microservice.poster.infrastructure.config;

import com.movies.microservice.poster.application.inputports.*;
import com.movies.microservice.poster.application.outputports.messaging.PosterEventPublisherOutputPort;
import com.movies.microservice.poster.application.outputports.persistence.PosterRepositorioOutputPort;
import com.movies.microservice.poster.application.outputports.query.PeliculaExistenciaOutputPort;
import com.movies.microservice.poster.application.outputports.storage.PosterStorageOutputPort;
import com.movies.microservice.poster.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PosterUseCasesConfig {

    @Bean
    public CrearPosterInputPort crearPosterUC(PosterRepositorioOutputPort repo, PosterStorageOutputPort storage,
            PeliculaExistenciaOutputPort peliculaQuery, PosterEventPublisherOutputPort pub) {
        return new CrearPosterUseCase(repo, storage, peliculaQuery, pub);
    }

    @Bean
    public ActualizarPosterInputPort actualizarPosterUC(PosterRepositorioOutputPort repo, PosterStorageOutputPort storage) {
        return new ActualizarPosterUseCase(repo, storage);
    }

    @Bean
    public EliminarPosterInputPort eliminarPosterUC(PosterRepositorioOutputPort repo, PosterStorageOutputPort storage) {
        return new EliminarPosterUseCase(repo, storage);
    }

    @Bean
    public ActivarPosterInputPort activarPosterUC(PosterRepositorioOutputPort repo, PosterEventPublisherOutputPort pub) {
        return new ActivarPosterUseCase(repo, pub);
    }

    @Bean
    public DesactivarPosterInputPort desactivarPosterUC(PosterRepositorioOutputPort repo, PosterEventPublisherOutputPort pub) {
        return new DesactivarPosterUseCase(repo, pub);
    }

    @Bean
    ListarPostersPorPeliculaInputPort listarPostersUC(PosterRepositorioOutputPort repo) {
        return new ListarPostersPorPeliculaUseCase(repo);
    }
}
