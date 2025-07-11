package com.aot.fantomeapp.service;

import com.aot.fantomeapp.dto.PageComponentCreateDto;
import com.aot.fantomeapp.dto.PageComponentDto;
import com.aot.fantomeapp.mapper.PageComponentMapper;
import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.model.Section;
import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import com.aot.fantomeapp.repository.PageComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageComponentService {

   private final PageComponentRepository pageComponentRepository;
   private final PageComponentMapper pageComponentMapper;
   protected final SectionService sectionService;

   public void create(Long sectionId, PageComponentCreateDto dto) {
      PageComponent pageComponentToSave = new PageComponent();
      
      Optional<Section> sectionOpt = sectionService.findById(sectionId);
      if (sectionOpt.isEmpty()) {
         throw new RuntimeException("Section not found");
      }
      pageComponentToSave.setSection(sectionOpt.get());
      
      if (!(dto.type().equals(ComponentType.PAGE_1) || dto.type().equals(ComponentType.PAGE_2) || dto.type().equals(ComponentType.PAGE_3) 
         || dto.type().equals(ComponentType.PAGE_4) || dto.type().equals(ComponentType.PAGE_5))) {
         Optional<PageComponent> parentOpt = findById(dto.parentId());
         if (parentOpt.isEmpty()) {
            throw new RuntimeException("Parent component not found");
         }
         pageComponentToSave.setParent(parentOpt.get());
      }
      
      pageComponentToSave.setCode(dto.code());
      pageComponentToSave.setType(dto.type());
      pageComponentToSave.setPosition(dto.position());
      pageComponentToSave.setStatus(ComponentStatus.DRAFT);
      pageComponentToSave.setCreatedAt(Instant.now());
      pageComponentRepository.save(pageComponentToSave);
   }

   public List<PageComponentDto> findAllBySectionId(Long sectionId) {
      return pageComponentMapper.toDto(pageComponentRepository.findAllBySectionId(sectionId));
   }
   
   public void affectNextPageComponent(Long currentPageComponentId, Long nextPageComponentId) {
      findById(currentPageComponentId).ifPresent(currentPageComponent -> {
         findById(nextPageComponentId).ifPresent(nextPageComponent -> {
            currentPageComponent.setNext(nextPageComponent);
            currentPageComponent.setUpdatedAt(Instant.now());
         });
         pageComponentRepository.save(currentPageComponent);
      });
   }

   public Optional<PageComponent> findById(Long id) {
      return pageComponentRepository.findById(id);
   }

   public PageComponentDto findRootBySectionId(Long sectionId) {
      Optional<Section> sectionOpt = sectionService.findById(sectionId);
      if (sectionOpt.isEmpty()) {
         throw new RuntimeException("Section not found");
      }
      
      ComponentType typeBySection = ComponentType.PAGE_1;
      if (sectionOpt.get().getCode().equals("secure-myself")) {
         typeBySection = ComponentType.PAGE_3;
      }
      List<PageComponent> pageComponents = pageComponentRepository.findAllBySectionIdAndStatusAndType(sectionId, ComponentStatus.PUBLISHED, typeBySection);
      List<Long> nextComponentIds = pageComponents.stream().map(PageComponent::getNext).filter(Objects::nonNull).map(PageComponent::getId).toList();
      Optional<PageComponent> rootComponentOpt = pageComponents.stream().filter(pageComponent -> pageComponent.getParent() == null &&
         !nextComponentIds.contains(pageComponent.getId())).findFirst();

      return rootComponentOpt.map(pageComponentMapper::toDto).orElse(null);
   }

   public void delete(Long pageComponentId) {
      findById(pageComponentId).ifPresentOrElse(pageComponentRepository::delete, ()-> {
         throw new RuntimeException("Page component not found");
      });
   }
}
