package com.aot.fantomeapp.config.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aot.fantomeapp.model.PageComponentTranslationLight;
import com.aot.fantomeapp.repository.PageComponentTranslationLightRepository;

import lombok.RequiredArgsConstructor;

@Service("securityService")
@RequiredArgsConstructor
public class SecurityService {

    private final PageComponentTranslationLightRepository pageComponentTranslationLightRepository;

    public boolean isAuthorizedOnComponentTranslation(Long pageComponentTranslationId) {
        ConnectedUser user = (ConnectedUser) SecurityContextHolder.getContext().getAuthentication();
        if (user.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
            return true;
        }
        PageComponentTranslationLight pageComponentTranslation = 
            pageComponentTranslationLightRepository
                .findById(pageComponentTranslationId)
                .orElseThrow(() -> new RuntimeException("Page component translation not found"));
        String language = pageComponentTranslation.getCountryRegion().name();
        if (user.getLanguages().contains(language)) {
            return true;
        }
        return false;
    }
}
