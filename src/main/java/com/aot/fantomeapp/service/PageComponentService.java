package com.aot.fantomeapp.service;

import com.aot.fantomeapp.dto.PageComponentCreateDto;
import com.aot.fantomeapp.dto.PageComponentDto;
import com.aot.fantomeapp.mapper.PageComponentMapper;
import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.model.Section;
import com.aot.fantomeapp.model.enums.ComponentStatus;
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

   public void create(Long sectionId, PageComponentCreateDto pageComponentCreateDto) {
      PageComponent pageComponentToSave = new PageComponent();
      Section section = new Section();
      section.setId(sectionId);
      pageComponentToSave.setSection(section);
      pageComponentToSave.setCode(pageComponentCreateDto.code());
      pageComponentToSave.setType(pageComponentCreateDto.type());
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

   public void updateParentPageComponent(Long currentPageComponentId, Long parentPageComponentId) {
      findById(currentPageComponentId).ifPresent(currentPageComponent -> {
         findById(parentPageComponentId).ifPresent(parentPageComponent -> {
            currentPageComponent.setParent(parentPageComponent);
            currentPageComponent.setUpdatedAt(Instant.now());
         });
         pageComponentRepository.save(currentPageComponent);
      });
   }

   public PageComponentDto findRootBySectionId(Long sectionId) {
      List<PageComponent> pageComponents = pageComponentRepository.findAllBySectionIdAndStatus(sectionId, ComponentStatus.PUBLISHED);
      List<Long> nextComponentIds = pageComponents.stream().map(PageComponent::getNext).filter(Objects::nonNull).map(PageComponent::getId).toList();
      Optional<PageComponent> rootComponentOpt = pageComponents.stream().filter(pageComponent -> pageComponent.getParent() == null &&
         !nextComponentIds.contains(pageComponent.getId())).findFirst();

      return rootComponentOpt.map(pageComponentMapper::toDto).orElse(null);
   }
}
