package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.model.Section;
import com.aot.fantomeapp.service.SectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/sections")
@RequiredArgsConstructor
public class SectionController {

   private final SectionService sectionService;
   
   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping
   public ResponseEntity<List<Section>> getSections() {
      log.debug("getSections");
      List<Section> result = sectionService.findAll();
      return ResponseEntity.ok(result);
   }
}
