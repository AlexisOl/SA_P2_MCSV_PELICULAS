package com.movies.microservice.detallecategoria.infrastructure.config;

import com.movies.microservice.detallecategoria.application.inputports.*;
import com.movies.microservice.detallecategoria.application.outputports.messaging.DetalleCategoriaEventPublisherOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.persistence.DetalleCategoriaRepositorioOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.query.CategoriaQueryOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.query.PeliculaExistenciaOutputPort;
import com.movies.microservice.detallecategoria.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetalleCategoriaUseCasesConfig {

    @Bean
    public AsignarCategoriaAPeliculaInputPort asignarUC(
            DetalleCategoriaRepositorioOutputPort repo,
            PeliculaExistenciaOutputPort peliculaQuery,
            CategoriaQueryOutputPort categoriaQuery,
            DetalleCategoriaEventPublisherOutputPort pub) {
        return new AsignarCategoriaAPeliculaUseCase(repo, peliculaQuery, categoriaQuery, pub);
    }

    @Bean
    public QuitarCategoriaDePeliculaInputPort quitarUC(
            DetalleCategoriaRepositorioOutputPort repo,
            DetalleCategoriaEventPublisherOutputPort pub) {
        return new QuitarCategoriaDePeliculaUseCase(repo, pub);
    }

    @Bean
    public ReemplazarCategoriasDePeliculaInputPort reemplazarUC(
            DetalleCategoriaRepositorioOutputPort repo,
            PeliculaExistenciaOutputPort peliculaQuery,
            CategoriaQueryOutputPort categoriaQuery,
            DetalleCategoriaEventPublisherOutputPort pub) {
        return new ReemplazarCategoriasDePeliculaUseCase(repo, peliculaQuery, categoriaQuery, pub);
    }

    @Bean
    public ListarCategoriasDePeliculaInputPort listarCategoriasDePeliculaUC(DetalleCategoriaRepositorioOutputPort repo) {
        return new ListarCategoriasDePeliculaUseCase(repo);
    }

    @Bean
    public ListarPeliculasPorCategoriaInputPort listarPeliculasUCDetalle(DetalleCategoriaRepositorioOutputPort repo) {
        return new ListarPeliculasPorCategoriaUseCase(repo);
    }
}
