
package com.movies.microservice.horario.infrastructure.config;

import com.movies.microservice.horario.application.inputports.*;
import com.movies.microservice.horario.application.outputports.eventos.NotificarHorarioOutputPort;
import com.movies.microservice.horario.application.outputports.persistence.HorarioRepositorioOutputPort;
import com.movies.microservice.horario.application.outputports.query.ValidacionesExternaOutputPort;
import com.movies.microservice.horario.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HorarioUseCasesConfig {
  @Bean 
  public CrearHorarioInputPort crearHorarioUC(HorarioRepositorioOutputPort repo, ValidacionesExternaOutputPort val, NotificarHorarioOutputPort notificar) {
    return new CrearHorarioUseCase(repo, val, notificar);
  }
  @Bean 
  public ActualizarHorarioInputPort actualizarHorarioUC(HorarioRepositorioOutputPort repo) {
    return new ActualizarHorarioUseCase(repo);
  }
  @Bean 
  public DesactivarHorarioInputPort desactivarHorarioUC(HorarioRepositorioOutputPort repo) {
    return new DesactivarHorarioUseCase(repo);
  }
  @Bean 
  public ListarHorariosInputPort listarHorariosUC(HorarioRepositorioOutputPort repo) {
    return new ListarHorariosUseCase(repo);
  }
}
