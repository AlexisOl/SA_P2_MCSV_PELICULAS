package com.movies.microservice.pelicula.infrastructure.inputadapters.rest.mapper;

import com.movies.microservice.pelicula.application.commands.CrearPeliculaCommand;
import com.movies.microservice.pelicula.application.commands.ActualizarPeliculaCommand;
import com.movies.microservice.pelicula.domain.Pelicula;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto.PeliculaRequest;
import com.movies.microservice.pelicula.infrastructure.inputadapters.rest.dto.PeliculaResponse;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 17fa7f3 (first commit)
>>>>>>> dev

import java.util.UUID;

public class PeliculaRestMapper {

    public static CrearPeliculaCommand toCreateCmd(PeliculaRequest r) {
        return CrearPeliculaCommand.builder()
                .titulo(r.getTitulo())
                .sinopsis(r.getSinopsis())
                .duracion(r.getDuracion() != null ? r.getDuracion() : 0)
                .posters(r.getPosters())
                .cast(r.getCast())
                .director(r.getDirector())
                .clasificacion(r.getClasificacion())
                .activa(r.getActiva() != null && r.getActiva())
                .fechaEstreno(r.getFechaEstreno())
                .categorias(r.getCategorias())
                .build();
    }

    public static ActualizarPeliculaCommand toUpdateCmd(UUID id, PeliculaRequest r) {
        return ActualizarPeliculaCommand.builder()
                .peliculaId(id)
                .titulo(r.getTitulo())
                .sinopsis(r.getSinopsis())
                .duracion(r.getDuracion())
                .posters(r.getPosters())
                .cast(r.getCast())
                .director(r.getDirector())
                .clasificacion(r.getClasificacion())
                .fechaEstreno(r.getFechaEstreno())
                .categorias(r.getCategorias())
                .activa(r.getActiva())
                .build();
    }

    public static PeliculaResponse toResponse(Pelicula p) {
        return PeliculaResponse.builder()
                .id(p.getId().getValue())
                .titulo(p.getTitulo())
                .sinopsis(p.getSinopsis())
                .duracion(p.getDuracion())
                .posters(p.getPosters())
                .cast(p.getCast())
                .director(p.getDirector())
                .clasificacion(p.getClasificacion())
                .activa(p.isActiva())
                .fechaEstreno(p.getFechaEstreno())
                .build();
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
    
    public static PeliculaResponse toResponse(Pelicula p, List<String> posters) {
        return PeliculaResponse.builder()
                .id(p.getId().getValue())               // o p.getId().id()
                .titulo(p.getTitulo())
                .sinopsis(p.getSinopsis())
                .duracion(p.getDuracion())
                .posters(posters)                       // ✅ asignamos los reales
                .cast(p.getCast())
                .director(p.getDirector())
                .clasificacion(p.getClasificacion())
                .activa(p.isActiva())
                .fechaEstreno(p.getFechaEstreno())
                .build();
    }
=======
>>>>>>> 17fa7f3 (first commit)
>>>>>>> dev
}
