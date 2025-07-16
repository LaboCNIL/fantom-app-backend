package com.aot.fantomeapp.service;

import com.aot.fantomeapp.dto.PageComponentTranslationCreateDto;
import com.aot.fantomeapp.model.PageComponentWithImage;
import com.aot.fantomeapp.model.PageComponentTranslationWithImage;
import com.aot.fantomeapp.model.enums.TranslationStatus;
import com.aot.fantomeapp.repository.PageComponentTranslationWithImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PageComponentTranslationService {

   private final PageComponentTranslationWithImageRepository pageComponentTranslationWithImageRepository;

   public void create(Long pageComponentId, PageComponentTranslationCreateDto dto) {
      PageComponentTranslationWithImage pageComponentTranslationToSave = new PageComponentTranslationWithImage();
      PageComponentWithImage pageComponent = new PageComponentWithImage();
      pageComponent.setId(pageComponentId);
      pageComponentTranslationToSave.setPageComponent(pageComponent);
      pageComponentTranslationToSave.setCountryRegion(dto.countryRegion());
      pageComponentTranslationToSave.setDevices(dto.devices());
      pageComponentTranslationToSave.setFirstTitle(dto.firstTitle());
      pageComponentTranslationToSave.setSecondTitle(dto.secondTitle());
      pageComponentTranslationToSave.setDescription(dto.description());
      pageComponentTranslationToSave.setImage(dto.image());
      pageComponentTranslationToSave.setStatus(TranslationStatus.DRAFT);
      pageComponentTranslationWithImageRepository.save(pageComponentTranslationToSave);
   }

   public void delete(Long pageComponentTranslationId) {
      pageComponentTranslationWithImageRepository.findById(pageComponentTranslationId).ifPresentOrElse(pageComponentTranslationWithImageRepository::delete, () -> {
         throw new RuntimeException("Page component translation not found");
      });
   }
}
