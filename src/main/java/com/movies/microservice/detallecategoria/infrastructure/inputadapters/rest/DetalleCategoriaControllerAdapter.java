package com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest;

import com.movies.microservice.detallecategoria.application.inputports.*;
import com.movies.microservice.detallecategoria.application.outputports.query.CategoriaQueryOutputPort;
import com.movies.microservice.detallecategoria.application.outputports.query.PeliculaQueryOutputPort;
import com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto.AsignacionRequest;
import com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto.CategoriaDePeliculaResponse;
import com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto.PeliculaPorCategoriaResponse;
import com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.dto.ReemplazoRequest;
import com.movies.microservice.detallecategoria.infrastructure.inputadapters.rest.mapper.DetalleCategoriaRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/detalle-categoria")
@RequiredArgsConstructor
public class DetalleCategoriaControllerAdapter {

    private final AsignarCategoriaAPeliculaInputPort asignar;
    private final QuitarCategoriaDePeliculaInputPort quitar;
    private final ReemplazarCategoriasDePeliculaInputPort reemplazar;
    private final ListarCategoriasDePeliculaInputPort listarCategorias;
    private final ListarPeliculasPorCategoriaInputPort listarPeliculas;

    // Asignar una categoría a una película
    @PostMapping("/peliculas/{peliculaId}/categorias")
    public ResponseEntity<Map<String, Object>> asignar(@PathVariable UUID peliculaId,
            @RequestBody AsignacionRequest req) {
        asignar.asignar(peliculaId, req.getCategoriaId());
        return ResponseEntity.ok(Map.of(
                "message", "Categoría asignada correctamente",
                "peliculaId", peliculaId,
                "categoriaId", req.getCategoriaId(),
                "timestamp", java.time.OffsetDateTime.now()
        ));
    }

    // Reemplazar el set completo de categorías de una película
    @PutMapping("/peliculas/{peliculaId}/categorias")
    public void reemplazar(@PathVariable UUID peliculaId, @RequestBody ReemplazoRequest req) {
        Set<UUID> set = req.getCategoriaIds() != null ? req.getCategoriaIds() : Set.of();
        reemplazar.reemplazar(peliculaId, set);
    }

    // Quitar una categoría de la película
    @DeleteMapping("/peliculas/{peliculaId}/categorias/{categoriaId}")
    public void quitar(@PathVariable UUID peliculaId, @PathVariable UUID categoriaId) {
        quitar.quitar(peliculaId, categoriaId);
    }

    private final CategoriaQueryOutputPort categoriaQuery;
    private final PeliculaQueryOutputPort peliculaQuery;

    @GetMapping("/peliculas/{peliculaId}/categorias")
    public List<CategoriaDePeliculaResponse> listarCategorias(@PathVariable UUID peliculaId) {
        var ids = listarCategorias.listarCategorias(peliculaId);
        var nombres = categoriaQuery.findNamesByIds(new java.util.HashSet<>(ids));
        return ids.stream()
                .map(id -> DetalleCategoriaRestMapper.toCategoriaResp(id, nombres.get(id)))
                .toList();
    }

    @GetMapping("/{categoriaId}/peliculas")
    public List<PeliculaPorCategoriaResponse> listarPeliculas(@PathVariable UUID categoriaId) {
        var ids = listarPeliculas.listarPeliculas(categoriaId);
        var titulos = peliculaQuery.findTitlesByIds(new java.util.HashSet<>(ids));
        return ids.stream()
                .map(id -> DetalleCategoriaRestMapper.toPeliculaResp(id, titulos.get(id)))
                .toList();
    }
}
