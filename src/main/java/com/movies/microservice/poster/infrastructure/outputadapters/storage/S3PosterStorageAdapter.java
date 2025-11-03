package com.movies.microservice.poster.infrastructure.outputadapters.storage;

import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.movies.microservice.poster.application.outputports.storage.PosterStorageOutputPort;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@RequiredArgsConstructor
public class S3PosterStorageAdapter implements PosterStorageOutputPort {

    private final S3Client s3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private static String sanitize(String original) {
        return URLEncoder.encode(original, StandardCharsets.UTF_8).replace("+", "%20");
    }

    private String keyFor(UUID posterId, String originalFilename) {
        String name = originalFilename != null ? originalFilename : posterId + ".bin";
        return "posters/" + posterId + "-" + sanitize(name);
    }

    @Override
public String upload(UUID posterId, MultipartFile file) {
    String key = keyFor(posterId, file.getOriginalFilename());
    try {
        
        String contentType = URLConnection.guessContentTypeFromName(file.getOriginalFilename());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

             s3.putObject(PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .contentType(contentType)        
                        // .acl(ObjectCannedACL.PUBLIC_READ)  <-- si lo usas
                        .build(),
                RequestBody.fromBytes(file.getBytes()));

        
        return "https://" + bucket + ".s3.amazonaws.com/" + key;
    } catch (IOException e) {
        throw new RuntimeException("Error leyendo archivo para S3", e);
    }
}

    @Override
    public String replace(UUID posterId, MultipartFile file, String oldUrl) {
        // sube el nuevo
        String newUrl = upload(posterId, file);
        // intenta borrar el anterior (best-effort)
        try {
            if (oldUrl != null && !oldUrl.isBlank()) {
                String key = oldUrl.substring(oldUrl.indexOf(".amazonaws.com/") + ".amazonaws.com/".length());
                s3.deleteObject(DeleteObjectRequest.builder().bucket(bucket).key(key).build());
            }
        } catch (Exception ignored) {
        }
        return newUrl;
    }

    @Override
    public void delete(String url) {
        if (url == null || url.isBlank()) {
            return;
        }
        String prefix = ".amazonaws.com/";
        int i = url.indexOf(prefix);
        if (i < 0) {
            return;
        }
        String key = url.substring(i + prefix.length());
        s3.deleteObject(DeleteObjectRequest.builder().bucket(bucket).key(key).build());
    }
}
