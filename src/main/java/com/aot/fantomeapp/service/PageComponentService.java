package com.aot.fantomeapp.service;

import com.aot.fantomeapp.dto.PageComponentCreateDto;
import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.model.Section;
import com.aot.fantomeapp.repository.PageComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageComponentService {
   
   private final PageComponentRepository pageComponentRepository;
   
   public void create(Long sectionId, PageComponentCreateDto pageComponentCreateDto) {
      PageComponent pageComponentToSave = new PageComponent();
      Section section = new Section();
      section.setId(sectionId);
      pageComponentToSave.setSection(section);
      pageComponentToSave.setCode(pageComponentCreateDto.code());
      pageComponentToSave.setType(pageComponentCreateDto.type());
      pageComponentToSave.setCreatedAt(Instant.now());
      pageComponentRepository.save(pageComponentToSave);
   }
   
   public List<PageComponent> findAllBySectionId(Long sectionId) {
      return pageComponentRepository.findAllBySectionId(sectionId);
   }
   
   public void affectNextPageComponent(Long currentPageComponentId, Long nextPageComponentId) {
      findById(currentPageComponentId).ifPresent(cuurentPageComponent -> {
         findById(nextPageComponentId).ifPresent(cuurentPageComponent::setNext);
         pageComponentRepository.save(cuurentPageComponent);
      });
   }
   
   public Optional<PageComponent> findById(Long id) {
      return pageComponentRepository.findById(id);
   }
}
