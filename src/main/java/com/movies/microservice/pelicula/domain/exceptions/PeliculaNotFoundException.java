<<<<<<< HEAD
=======
<<<<<<< HEAD
// pelicula/domain/exceptions/PeliculaNotFoundException.java
package com.movies.microservice.pelicula.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculaNotFoundException extends RuntimeException {

    public PeliculaNotFoundException(String msg) {
        super(msg);
=======
>>>>>>> dev
package com.movies.microservice.pelicula.domain.exceptions;

public class PeliculaNotFoundException extends RuntimeException {

    public PeliculaNotFoundException(String message) {
        super(message);
<<<<<<< HEAD
=======
>>>>>>> 17fa7f3 (first commit)
>>>>>>> dev
    }
}
