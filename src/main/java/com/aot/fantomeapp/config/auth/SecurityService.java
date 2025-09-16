package com.aot.fantomeapp.config.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aot.fantomeapp.dto.PageComponentTranslationCreateUpdateDto;
import com.aot.fantomeapp.model.PageComponentTranslationLight;
import com.aot.fantomeapp.model.enums.TranslationStatus;
import com.aot.fantomeapp.repository.PageComponentTranslationLightRepository;

import lombok.RequiredArgsConstructor;

@Service("securityService")
@RequiredArgsConstructor
public class SecurityService {

    private final PageComponentTranslationLightRepository pageComponentTranslationLightRepository;

    public boolean isAuthorizedOnTranslation(Long pageComponentTranslationId) {
        ConnectedUser user = (ConnectedUser) SecurityContextHolder.getContext().getAuthentication();
        if (isAdmin()) {
            return true;
        }
        PageComponentTranslationLight pageComponentTranslation = 
            pageComponentTranslationLightRepository
                .findById(pageComponentTranslationId)
                .orElseThrow(() -> new RuntimeException("Page component translation not found"));
        // Si la traduction est déjà published, on rejette
        if (TranslationStatus.PUBLISHED.name().equals(pageComponentTranslation.getStatus().name())) {
            return false;
        }
        String language = pageComponentTranslation.getCountryRegion().name();
        if (user.getLanguages().contains(language)) {
            return true;
        }
        return false;
    }

    public boolean isAuthorizedOnUpdateTranslation(Long pageComponentTranslationId, PageComponentTranslationCreateUpdateDto dto) {
        if (isAdmin()) {
            return true;
        }
        // le nouveau statut ne doit pas être published
        if (TranslationStatus.PUBLISHED.name().equals(dto.status().name())) {
            return false;
        }
        // on applique les mêmes filtres que pour la suppression
        return isAuthorizedOnTranslation(pageComponentTranslationId);
    }

    public boolean isAuthorizedOnCreateTranslation(PageComponentTranslationCreateUpdateDto dto) {
        if (isAdmin()) {
            return true;
        }
        // Si la traduction est déjà published, on rejette
        if (TranslationStatus.PUBLISHED.name().equals(dto.status().name())) {
            return false;
        }
        return true;
    }

    private boolean isAdmin() {
        ConnectedUser user = (ConnectedUser) SecurityContextHolder.getContext().getAuthentication();
        return user.getRoles()
                    .stream()
                    .anyMatch(role -> role.getAuthority().equals("ADMIN"));
        
    }
}
