package com.movies.microservice.categoria.infrastructure.config;

import com.movies.microservice.categoria.application.inputports.*;
import com.movies.microservice.categoria.application.outputports.messaging.CategoriaEventPublisherOutputPort;
import com.movies.microservice.categoria.application.outputports.persistence.CategoriaRepositorioOutputPort;
import com.movies.microservice.categoria.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaUseCasesConfig {

    @Bean
    public CrearCategoriaInputPort crearCategoriaUC(CategoriaRepositorioOutputPort repo, CategoriaEventPublisherOutputPort pub) {
        return new CrearCategoriaUseCase(repo, pub);
    }

    @Bean
    public EditarCategoriaInputPort editarCategoriaUC(CategoriaRepositorioOutputPort repo, CategoriaEventPublisherOutputPort pub) {
        return new EditarCategoriaUseCase(repo, pub);
    }

    @Bean
    public ActivarCategoriaInputPort activarCategoriaUC(CategoriaRepositorioOutputPort repo, CategoriaEventPublisherOutputPort pub) {
        return new ActivarCategoriaUseCase(repo, pub);
    }

    @Bean
    public DesactivarCategoriaInputPort desactivarCategoriaUC(CategoriaRepositorioOutputPort repo, CategoriaEventPublisherOutputPort pub) {
        return new DesactivarCategoriaUseCase(repo, pub);
    }

    @Bean
    public ListarCategoriasInputPort listarCategoriasUC(CategoriaRepositorioOutputPort repo) {
        return new ListarCategoriasUseCase(repo);
    }
}
