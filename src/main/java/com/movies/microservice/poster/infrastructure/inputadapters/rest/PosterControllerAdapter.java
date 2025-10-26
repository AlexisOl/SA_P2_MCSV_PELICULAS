package com.movies.microservice.poster.infrastructure.inputadapters.rest;

import com.movies.microservice.poster.application.inputports.*;
import com.movies.microservice.poster.infrastructure.inputadapters.rest.dto.PosterResponse;
import com.movies.microservice.poster.infrastructure.inputadapters.rest.mapper.PosterRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posters")
@RequiredArgsConstructor
public class PosterControllerAdapter {

    private final CrearPosterInputPort crear;
    private final ActualizarPosterInputPort actualizar;
    private final EliminarPosterInputPort eliminar;
    private final ActivarPosterInputPort activar;
    private final DesactivarPosterInputPort desactivar;
    private final ListarPostersPorPeliculaInputPort listar;

    // Crear (multipart)
    @PostMapping(
  value = "/peliculas/{peliculaId}/posters",
  consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public ResponseEntity<PosterResponse> crear(
    @PathVariable UUID peliculaId,
    @RequestParam("file") MultipartFile file,
    @RequestParam(value = "orden", required = false) Integer orden) {

  var poster = crear.crear(peliculaId, file, orden);
  return ResponseEntity.status(HttpStatus.CREATED).body(PosterRestMapper.toResponse(poster));
}

    // Reemplazar archivo (multipart)
    @PutMapping(value = "/{posterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PosterResponse actualizar(@PathVariable UUID posterId, @RequestPart("file") MultipartFile file) {
        return PosterRestMapper.toResponse(actualizar.actualizar(posterId, file));
    }

    // Eliminar (borra DB + S3)
    @DeleteMapping("/{posterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable UUID posterId) {
        eliminar.eliminar(posterId);
    }

    // Activar / Desactivar
    @PostMapping("/{posterId}/activar")
    public PosterResponse activar(@PathVariable UUID posterId) {
        return PosterRestMapper.toResponse(activar.activar(posterId));
    }

    @PostMapping("/{posterId}/desactivar")
    public PosterResponse desactivar(@PathVariable UUID posterId) {
        return PosterRestMapper.toResponse(desactivar.desactivar(posterId));
    }

    // Listar por película
    @GetMapping("/peliculas/{peliculaId}/posters")
    public List<PosterResponse> listar(@PathVariable UUID peliculaId,
            @RequestParam(required = false, defaultValue = "true") Boolean activosSolo) {
        return listar.listar(peliculaId, activosSolo).stream().map(PosterRestMapper::toResponse).toList();
    }
}
