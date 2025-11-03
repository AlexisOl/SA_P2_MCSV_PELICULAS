package com.movies.microservice.pelicula.infrastructure.inputadapters.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.microservice.pelicula.application.commands.ListarPeliculasQuery;
import com.movies.microservice.pelicula.application.inputports.ActivarPeliculaInputPort;
import com.movies.microservice.pelicula.application.inputports.ActualizarPeliculaInputPort;
import com.movies.microservice.pelicula.application.inputports.CrearPeliculaInputPort;
import com.movies.microservice.pelicula.application.inputports.DesactivarPeliculaInputPort;
import com.movies.microservice.pelicula.application.inputports.ListarPeliculasInputPort;
import com.movies.microservice.pelicula.application.outputports.query.PosterQueryOutputPort;
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto.PeliculaRequest;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto.PeliculaResponse;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.mapper.PeliculaRestMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/peliculas")
@RequiredArgsConstructor
public class PeliculaControllerAdapter {

    private final CrearPeliculaInputPort crear;
    private final ActualizarPeliculaInputPort actualizar;
    private final ActivarPeliculaInputPort activar;
    private final DesactivarPeliculaInputPort desactivar;
    private final ListarPeliculasInputPort listar;

    @PostMapping
    public PeliculaResponse crear(@RequestBody PeliculaRequest req) {
        Pelicula p = crear.crear(PeliculaRestMapper.toCreateCmd(req));
        return PeliculaRestMapper.toResponse(p);
    }

    @PutMapping("/{id}")
    public PeliculaResponse actualizar(@PathVariable UUID id, @RequestBody PeliculaRequest req) {
        Pelicula p = actualizar.actualizar(PeliculaRestMapper.toUpdateCmd(id, req));
        return PeliculaRestMapper.toResponse(p);
    }

    @PostMapping("/{id}/activar")
    public PeliculaResponse activar(@PathVariable UUID id) {
        Pelicula p = activar.activar(id);
        return PeliculaRestMapper.toResponse(p);
    }

    @PostMapping("/{id}/desactivar")
    public PeliculaResponse desactivar(@PathVariable UUID id) {
        Pelicula p = desactivar.desactivar(id);
        return PeliculaRestMapper.toResponse(p);
    }

    //private final ListarPeliculasInputPort listar;
    private final PosterQueryOutputPort posterQuery; // <<— nuevo

    @GetMapping
    public List<PeliculaResponse> listar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String clasificacion,
            @RequestParam(required = false) Boolean activa,
            @RequestParam(required = false) String releasedFrom,
            @RequestParam(required = false) String releasedTo,
            @RequestParam(required = false) List<String> categorias,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "fechaEstreno,desc") String sort
    ) {
        var query = ListarPeliculasQuery.builder()
                .texto(q)
                .clasificacion(clasificacion)
                .activa(activa)
                .releasedFrom(releasedFrom != null ? java.time.LocalDate.parse(releasedFrom) : null)
                .releasedTo(releasedTo != null ? java.time.LocalDate.parse(releasedTo) : null)
                .categorias(categorias)
                .page(page).size(size).sort(sort)
                .build();

        return listar.listar(query).stream()
        .map(p -> {
            var urls = posterQuery.findUrlsByPelicula(p.getId().getValue());
            return PeliculaRestMapper.toResponse(p, urls); 
        })
        .toList();
    }
}