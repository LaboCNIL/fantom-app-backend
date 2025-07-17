package com.aot.fantomeapp.service;

import com.aot.fantomeapp.dto.PageComponentTranslationCreateUpdateDto;
import com.aot.fantomeapp.dto.PageComponentTranslationWithImageDto;
import com.aot.fantomeapp.mapper.PageComponentTranslationMapper;
import com.aot.fantomeapp.model.PageComponentTranslationWithImage;
import com.aot.fantomeapp.model.PageComponentWithImage;
import com.aot.fantomeapp.model.enums.TranslationStatus;
import com.aot.fantomeapp.repository.PageComponentTranslationWithImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageComponentTranslationService {

   private final PageComponentTranslationWithImageRepository pageComponentTranslationWithImageRepository;
   private final PageComponentTranslationMapper pageComponentTranslationMapper;
   private final PageComponentService pageComponentService;
   
   public void create(Long pageComponentId, PageComponentTranslationCreateUpdateDto dto) {
      Optional<PageComponentWithImage> pageComponentOpt = pageComponentService.findByIdWithImage(pageComponentId);
      if (pageComponentOpt.isEmpty()){
         throw new RuntimeException("Page component with id " + pageComponentId + " not found");
      }
      
      PageComponentTranslationWithImage pageComponentTranslationToSave = new PageComponentTranslationWithImage();
      pageComponentTranslationToSave.setPageComponent(pageComponentOpt.get());
      pageComponentTranslationToSave.setCountryRegion(dto.countryRegion());
      pageComponentTranslationToSave.setDevices(dto.devices());
      pageComponentTranslationToSave.setFirstTitle(dto.firstTitle());
      pageComponentTranslationToSave.setSecondTitle(dto.secondTitle());
      pageComponentTranslationToSave.setDescription(dto.description());
      pageComponentTranslationToSave.setImage(dto.image());
      pageComponentTranslationToSave.setStatus(dto.status());
      pageComponentTranslationWithImageRepository.save(pageComponentTranslationToSave);
   }

   public void delete(Long pageComponentTranslationId) {
      pageComponentTranslationWithImageRepository.findById(pageComponentTranslationId).ifPresentOrElse(pageComponentTranslationWithImageRepository::delete, () -> {
         throw new RuntimeException("Page component translation not found");
      });
   }

   public PageComponentTranslationWithImageDto findByIdWithImage(Long pageComponentTranslationId) {
      Optional<PageComponentTranslationWithImage> optional = pageComponentTranslationWithImageRepository.findById(pageComponentTranslationId);
      if (optional.isEmpty()) {
         throw new RuntimeException("Page component translation not found");
      }
      return pageComponentTranslationMapper.toDtoWithImage(optional.get());
   }

   public void update(Long pageComponentTranslationId, PageComponentTranslationCreateUpdateDto dto) {
      pageComponentTranslationWithImageRepository.findById(pageComponentTranslationId).ifPresentOrElse(pageComponentTranslation -> {
         pageComponentTranslation.setCountryRegion(dto.countryRegion());
         pageComponentTranslation.setDevices(dto.devices());
         pageComponentTranslation.setStatus(dto.status());
         pageComponentTranslation.setFirstTitle(dto.firstTitle());
         pageComponentTranslation.setSecondTitle(dto.secondTitle());
         pageComponentTranslation.setDescription(dto.description());
         pageComponentTranslation.setImage(dto.image());
         pageComponentTranslation.setUpdatedAt(Instant.now());

         pageComponentTranslationWithImageRepository.save(pageComponentTranslation);
      }, () -> {
         throw new RuntimeException("Page component translation not found");
      });
   }
}
