
package com.movies.microservice.poster.application.inputports;

import com.movies.microservice.poster.domain.entities.Poster;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public interface CrearPosterInputPort {

    Poster crear(UUID peliculaId, MultipartFile archivo, Integer orden);
}
