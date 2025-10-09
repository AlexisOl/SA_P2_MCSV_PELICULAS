package com.movies.microservice.common.exception;

import java.net.URI;
import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.movies.microservice.categoria.domain.exceptions.CategoriaAlreadyExistsException;
import com.movies.microservice.categoria.domain.exceptions.CategoriaNotFoundException;
import com.movies.microservice.detallecategoria.domain.exceptions.DetalleCategoriaAlreadyExistsException;
import com.movies.microservice.detallecategoria.domain.exceptions.DetalleCategoriaNotFoundException;
import com.movies.microservice.pelicula.domain.exceptions.PeliculaNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // 404: Película / Categoría / Detalle no encontrado
  @ExceptionHandler({
      PeliculaNotFoundException.class,
      CategoriaNotFoundException.class,
      DetalleCategoriaNotFoundException.class
  })
  public ProblemDetail handleNotFound(RuntimeException ex, WebRequest req) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    pd.setTitle("Recurso no encontrado");
    pd.setDetail(ex.getMessage());
    pd.setType(URI.create("https://http.dev/errors/not-found"));
    enrich(pd, req);
    return pd;
  }

  // 409: Conflictos (duplicados, ya existe)
  @ExceptionHandler({
      CategoriaAlreadyExistsException.class,
      DetalleCategoriaAlreadyExistsException.class
  })
  public ProblemDetail handleConflict(RuntimeException ex, WebRequest req) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    pd.setTitle("Conflicto");
    pd.setDetail(ex.getMessage());
    pd.setType(URI.create("https://http.dev/errors/conflict"));
    enrich(pd, req);
    return pd;
  }

  // 400: Validaciones / argumentos inválidos
  @ExceptionHandler({
      IllegalArgumentException.class,
      MethodArgumentTypeMismatchException.class
  })
  public ProblemDetail handleBadRequest(Exception ex, WebRequest req) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    pd.setTitle("Solicitud inválida");
    pd.setDetail(ex.getMessage());
    pd.setType(URI.create("https://http.dev/errors/bad-request"));
    enrich(pd, req);
    return pd;
  }

  // 400: errores de @Valid (si usas Bean Validation en DTOs)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidation(MethodArgumentNotValidException ex, WebRequest req) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    pd.setTitle("Datos inválidos");
    pd.setType(URI.create("https://http.dev/errors/validation"));
    var errors = ex.getBindingResult().getFieldErrors().stream()
        .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
        .toList();
    pd.setDetail("Hay errores de validación");
    pd.setProperty("errors", errors);
    enrich(pd, req);
    return pd;
  }

  // Fallback 500
  @ExceptionHandler(Throwable.class)
  public ProblemDetail handleAny(Throwable ex, WebRequest req) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    pd.setTitle("Error interno");
    pd.setDetail("Ocurrió un error inesperado");
    pd.setType(URI.create("https://http.dev/errors/internal"));
    enrich(pd, req);
    // log real aquí
    ex.printStackTrace();
    return pd;
  }

  private void enrich(ProblemDetail pd, WebRequest req) {
    pd.setProperty("timestamp", OffsetDateTime.now());
    pd.setProperty("path", req.getDescription(false).replace("uri=", ""));
    // si tienes correlation-id, agrégalo aquí
  }
}