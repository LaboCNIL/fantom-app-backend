package com.aot.fantomeapp.service;

import com.aot.fantomeapp.dto.PageComponentCreateDto;
import com.aot.fantomeapp.dto.PageComponentDto;
import com.aot.fantomeapp.dto.PageComponentUpdateDto;
import com.aot.fantomeapp.dto.PageComponentWithImageDto;
import com.aot.fantomeapp.mapper.PageComponentMapper;
import com.aot.fantomeapp.model.PageComponentWithImage;
import com.aot.fantomeapp.model.PageComponentLight;
import com.aot.fantomeapp.model.Section;
import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import com.aot.fantomeapp.repository.PageComponentLightRepository;
import com.aot.fantomeapp.repository.PageComponentWithImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageComponentService {

   private final PageComponentWithImageRepository pageComponentWithImageRepository;
   private final PageComponentLightRepository pageComponentLightRepository;
   private final PageComponentMapper pageComponentMapper;

   protected final SectionService sectionService;

   public void create(Long sectionId, PageComponentCreateDto dto) {
      PageComponentWithImage pageComponentToSave = new PageComponentWithImage();
      
      Optional<Section> sectionOpt = sectionService.findById(sectionId);
      if (sectionOpt.isEmpty()) {
         throw new RuntimeException("Section not found");
      }
      pageComponentToSave.setSection(sectionOpt.get());
      
      if (!(dto.type().equals(ComponentType.PAGE_1) || dto.type().equals(ComponentType.PAGE_2) || dto.type().equals(ComponentType.PAGE_3) 
         || dto.type().equals(ComponentType.PAGE_4) || dto.type().equals(ComponentType.PAGE_5))) {
         Optional<PageComponentWithImage> parentOpt = pageComponentWithImageRepository.findById(dto.parentId());
         if (parentOpt.isEmpty()) {
            throw new RuntimeException("Parent component not found");
         }
         pageComponentToSave.setParent(parentOpt.get());
      }
      
      pageComponentToSave.setCode(dto.code());
      pageComponentToSave.setType(dto.type());
      pageComponentToSave.setPosition(dto.position());
      pageComponentToSave.setStatus(ComponentStatus.DRAFT);
      pageComponentWithImageRepository.save(pageComponentToSave);
   }

   public List<PageComponentDto> findAllLightBySectionId(Long sectionId) {
      return pageComponentMapper.toDtoLight(pageComponentLightRepository.findAllBySectionId(sectionId));
   }
   
   public void affectNextPageComponent(Long currentPageComponentId, Long nextPageComponentId) {
      pageComponentWithImageRepository.findById(currentPageComponentId).ifPresent(currentPageComponent -> {
         pageComponentWithImageRepository.findById(nextPageComponentId).ifPresent(nextPageComponent -> {
            currentPageComponent.setNext(nextPageComponent);
            currentPageComponent.setUpdatedAt(Instant.now());
         });
         pageComponentWithImageRepository.save(currentPageComponent);
      });
   }

   public PageComponentDto findById(Long id) {
      Optional<PageComponentLight> pageComponentOpt = pageComponentLightRepository.findById(id);
      if (pageComponentOpt.isEmpty()) {
         throw new RuntimeException("Page component not found");
      }
      return pageComponentMapper.toDtoLight(pageComponentOpt.get());
   }

   public PageComponentWithImageDto findRootBySectionId(Long sectionId) {
      Optional<Section> sectionOpt = sectionService.findById(sectionId);
      if (sectionOpt.isEmpty()) {
         throw new RuntimeException("Section not found");
      }
      
      ComponentType typeBySection = ComponentType.PAGE_1;
      if (sectionOpt.get().getCode().equals("secure-myself")) {
         typeBySection = ComponentType.PAGE_3;
      }
      List<PageComponentWithImage> pageComponents = pageComponentWithImageRepository.findAllBySectionIdAndStatusAndType(sectionId, ComponentStatus.PUBLISHED, typeBySection);
      List<Long> nextComponentIds = pageComponents.stream().map(PageComponentWithImage::getNext).filter(Objects::nonNull).map(PageComponentWithImage::getId).toList();
      Optional<PageComponentWithImage> rootComponentOpt = pageComponents.stream().filter(pageComponent -> pageComponent.getParent() == null &&
         !nextComponentIds.contains(pageComponent.getId())).findFirst();

      return rootComponentOpt.map(pageComponentMapper::toDtoWithImage).orElse(null);
   }

   public void delete(Long pageComponentId) {
      pageComponentWithImageRepository.findById(pageComponentId).ifPresentOrElse(pageComponentWithImageRepository::delete, ()-> {
         throw new RuntimeException("Page component not found");
      });
   }

   public void update(Long pageComponentId, PageComponentUpdateDto dto) {
      Optional<PageComponentWithImage> optional = pageComponentWithImageRepository.findById(pageComponentId);
      if (optional.isEmpty()) {
         throw new RuntimeException("Page component not found");
      }
      PageComponentWithImage pageComponent = optional.get();
      
      Optional<Section> sectionOpt = sectionService.findById(dto.sectionId());
      if (sectionOpt.isEmpty()) {
         throw new RuntimeException("Section not found");
      }
      pageComponent.setSection(sectionOpt.get());
      
      if (!(dto.type().equals(ComponentType.PAGE_1) || dto.type().equals(ComponentType.PAGE_2) || dto.type().equals(ComponentType.PAGE_3)
         || dto.type().equals(ComponentType.PAGE_4) || dto.type().equals(ComponentType.PAGE_5))) {
         Optional<PageComponentWithImage> parentOpt = pageComponentWithImageRepository.findById(dto.parentId());
         if (parentOpt.isEmpty()) {
            throw new RuntimeException("Parent component not found");
         }
         pageComponent.setParent(parentOpt.get());
      }

      pageComponent.setCode(dto.code());
      pageComponent.setType(dto.type());
      pageComponent.setPosition(dto.position());
      pageComponent.setStatus(dto.status());
      pageComponentWithImageRepository.save(pageComponent);
   }
}
