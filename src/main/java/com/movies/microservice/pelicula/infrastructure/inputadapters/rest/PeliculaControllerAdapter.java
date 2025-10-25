package com.movies.microservice.pelicula.infrastructure.inputadapters.rest;

import com.movies.microservice.pelicula.application.commands.ListarPeliculasQuery;
import com.movies.microservice.pelicula.application.inputports.*;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import com.movies.microservice.pelicula.application.outputports.query.PosterQueryOutputPort;
=======
>>>>>>> 17fa7f3 (first commit)
>>>>>>> dev
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto.PeliculaRequest;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto.PeliculaResponse;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.mapper.PeliculaRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

<<<<<<< HEAD
=======
<<<<<<< HEAD
    //private final ListarPeliculasInputPort listar;
    private final PosterQueryOutputPort posterQuery; // <<— nuevo

=======
>>>>>>> 17fa7f3 (first commit)
>>>>>>> dev
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

<<<<<<< HEAD
        return listar.listar(query).stream().map(PeliculaRestMapper::toResponse).toList();
=======
<<<<<<< HEAD
        return listar.listar(query).stream()
        .map(p -> {
            var urls = posterQuery.findUrlsByPelicula(p.getId().getValue());
            return PeliculaRestMapper.toResponse(p, urls); 
        })
        .toList();
=======
        return listar.listar(query).stream().map(PeliculaRestMapper::toResponse).toList();
>>>>>>> 17fa7f3 (first commit)
>>>>>>> dev
    }
}
