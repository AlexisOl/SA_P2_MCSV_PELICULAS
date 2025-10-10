
package com.movies.microservice.poster.application.inputports;

import java.util.UUID;

public interface EliminarPosterInputPort {

    void eliminar(UUID posterId);
}
