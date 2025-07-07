package com.aot.fantomeapp.service;

import com.aot.fantomeapp.model.Section;
import com.aot.fantomeapp.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionService {
   
   private final SectionRepository sectionRepository;
   
   public List<Section> findAll() {
      return sectionRepository.findAll();
   }
   
   public Optional<Section> findById(Long id) {
      return sectionRepository.findById(id);
   }
}
