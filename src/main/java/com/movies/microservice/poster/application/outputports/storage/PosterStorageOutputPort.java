
package com.movies.microservice.poster.application.outputports.storage;

import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public interface PosterStorageOutputPort {

    String upload(UUID posterId, MultipartFile file);              // -> URL

    String replace(UUID posterId, MultipartFile file, String oldUrl);

    void delete(String url);
}
