package com.aot.fantomeapp.service;

import com.aot.fantomeapp.model.Image;
import com.aot.fantomeapp.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private static final Pattern DATA_URL_PATTERN = Pattern.compile("data:([^;]+);base64,(.*)");

    /**
     * Convertit une image en base64 (data URL) vers une entité Image en évitant les doublons
     * @param base64Image L'image au format "data:image/png;base64,..."
     * @return L'entité Image sauvegardée ou existante
     */
    public Image saveImageFromBase64(String base64Image) {
        if (base64Image == null || base64Image.trim().isEmpty()) {
            return null;
        }

        Matcher matcher = DATA_URL_PATTERN.matcher(base64Image);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Format d'image base64 invalide");
        }

        String mimeType = matcher.group(1);
        String base64Content = matcher.group(2);
        
        try {
            byte[] content = Base64.getDecoder().decode(base64Content);
            String hash = calculateMD5Hash(content);
            
            // Vérifier si l'image existe déjà
            Optional<Image> existingImage = imageRepository.findByHash(hash);
            if (existingImage.isPresent()) {
                return existingImage.get();
            }
            
            // Créer une nouvelle image
            Image image = new Image();
            image.setMimeType(mimeType);
            image.setContent(content);
            image.setHash(hash);
            
            return imageRepository.save(image);
            
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du traitement de l'image: " + e.getMessage(), e);
        }
    }

    /**
     * Convertit une entité Image vers une data URL base64
     * @param image L'entité Image
     * @return La data URL au format "data:image/png;base64,..."
     */
    public String convertImageToBase64(Image image) {
        if (image == null || image.getContent() == null) {
            return null;
        }
        
        String base64Content = Base64.getEncoder().encodeToString(image.getContent());
        return "data:" + image.getMimeType() + ";base64," + base64Content;
    }

    /**
     * Trouve une image par son ID
     * @param imageId L'ID de l'image
     * @return L'entité Image ou null si non trouvée
     */
    public Image findById(Long imageId) {
        if (imageId == null) {
            return null;
        }
        return imageRepository.findById(imageId).orElse(null);
    }

    /**
     * Calcule le hash MD5 d'un tableau de bytes
     * @param content Le contenu binaire
     * @return Le hash MD5 en hexadécimal
     */
    private String calculateMD5Hash(byte[] content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(content);
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithme MD5 non disponible", e);
        }
    }
} 