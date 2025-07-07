package com.aot.fantomeapp.service;

import com.aot.fantomeapp.dto.PageComponentTranslationCreateDto;
import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.model.PageComponentTranslation;
import com.aot.fantomeapp.repository.PageComponentTranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PageComponentTranslationService {
   
   private final PageComponentTranslationRepository pageComponentTranslationRepository;
   
   public void create(Long pageComponentId, PageComponentTranslationCreateDto dto) {
      PageComponentTranslation pageComponentTranslationToSave = new PageComponentTranslation();
      PageComponent pageComponent = new PageComponent();
      pageComponent.setId(pageComponentId);
      pageComponentTranslationToSave.setPageComponent(pageComponent);
      pageComponentTranslationToSave.setLanguage(dto.language());
      pageComponentTranslationToSave.setDevice(dto.device());
      pageComponentTranslationToSave.setFirstTitle(dto.firstTitle());
      pageComponentTranslationToSave.setSecondTitle(dto.secondTitle());
      pageComponentTranslationToSave.setDescription(dto.description());
      pageComponentTranslationToSave.setImage(dto.image());
      pageComponentTranslationToSave.setCreatedAt(Instant.now());
      pageComponentTranslationRepository.save(pageComponentTranslationToSave);
   }
}
