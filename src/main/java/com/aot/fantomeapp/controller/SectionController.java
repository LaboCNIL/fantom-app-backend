package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.model.Section;
import com.aot.fantomeapp.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sections")
@RequiredArgsConstructor
public class SectionController {
   
   private final SectionService sectionService;
   
   @GetMapping
   public ResponseEntity<List<Section>> getSections() {
      List<Section> result = sectionService.findAll();
      return ResponseEntity.ok(result);
   }
}
