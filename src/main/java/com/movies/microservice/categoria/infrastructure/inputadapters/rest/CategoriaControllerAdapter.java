package com.movies.microservice.categoria.infrastructure.inputadapters.rest;

import com.movies.microservice.categoria.application.inputports.*;
import com.movies.microservice.categoria.domain.entities.Categoria;
import com.movies.microservice.categoria.infrastructure.inputadapters.rest.dto.CategoriaRequest;
import com.movies.microservice.categoria.infrastructure.inputadapters.rest.dto.CategoriaResponse;
import com.movies.microservice.categoria.infrastructure.inputadapters.rest.mapper.CategoriaRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaControllerAdapter {

    private final CrearCategoriaInputPort crear;
    private final EditarCategoriaInputPort editar;
    private final ActivarCategoriaInputPort activar;
    private final DesactivarCategoriaInputPort desactivar;
    private final ListarCategoriasInputPort listar;

    @PostMapping
    public CategoriaResponse crear(@RequestBody CategoriaRequest req) {
        Categoria categoria = crear.crear(req.getNombre(), req.getActiva() != null && req.getActiva());
        return CategoriaRestMapper.toResponse(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaResponse editar(@PathVariable UUID id, @RequestBody CategoriaRequest req) {
        Categoria categoria = editar.editar(id, req.getNombre());
        return CategoriaRestMapper.toResponse(categoria);
    }

    @PostMapping("/{id}/activar")
    public CategoriaResponse activar(@PathVariable UUID id) {
        Categoria categoria = activar.activar(id);
        return CategoriaRestMapper.toResponse(categoria);
    }

    @PostMapping("/{id}/desactivar")
    public CategoriaResponse desactivar(@PathVariable UUID id) {
        Categoria categoria = desactivar.desactivar(id);
        return CategoriaRestMapper.toResponse(categoria);
    }

    @GetMapping
    public List<CategoriaResponse> listar(@RequestParam(required = false) Boolean activa) {
        return listar.listar(activa).stream().map(CategoriaRestMapper::toResponse).toList();
    }
}
