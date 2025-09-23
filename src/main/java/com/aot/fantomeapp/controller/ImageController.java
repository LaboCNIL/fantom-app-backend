package com.aot.fantomeapp.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aot.fantomeapp.model.Image;
import com.aot.fantomeapp.service.ImageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public/images")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    @Value("${app.imageCacheMaxAge}")
    private Long imageCacheMaxAge;

    private final ImageService imageService;

    /**
     * Endpoint pour servir les images de façon optimisée avec support ETag
     * @param imageId L'ID de l'image à récupérer
     * @param ifNoneMatch Header ETag pour la validation du cache (optionnel)
     * @return L'image avec les headers de cache appropriés ou 304 Not Modified
     */
    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable("imageId") Long imageId,
            @RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        log.debug("Demande d'image avec ID: {}", imageId);
        
        Image image = imageService.findById(imageId);
        if (image == null) {
            log.warn("Image non trouvée avec ID: {}", imageId);
            return ResponseEntity.notFound().build();
        }

        // Déterminer le type de contenu à partir du MIME type
        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(image.getMimeType());
        } catch (Exception e) {
            log.warn("Type MIME invalide pour l'image {}: {}", imageId, image.getMimeType());
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        // Configurer les headers de cache pour optimiser les performances
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setCacheControl(CacheControl.maxAge(imageCacheMaxAge, TimeUnit.SECONDS)
                .mustRevalidate()
                .cachePublic());
        
        // Ajouter un ETag basé sur le hash pour la validation du cache
        String etag = "\"" + image.getHash() + "\"";
        headers.setETag(etag);

        // Vérifier si le client a déjà cette version en cache (ETag validation)
        if (etag.equals(ifNoneMatch)) {
            log.debug("Image {} - client possède déjà la version actuelle (ETag: {})", imageId, etag);
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .headers(headers)
                    .build();
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(image.getContent());
    }
} 
