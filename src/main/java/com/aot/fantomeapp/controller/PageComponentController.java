package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.dto.PageComponentCreateDto;
import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.service.PageComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page-components")
@RequiredArgsConstructor
public class PageComponentController {
   
   private final PageComponentService pageComponentService;
   
   @GetMapping("{sectionId}")
   public ResponseEntity<List<PageComponent>> getPageComponents(@PathVariable("sectionId") Long sectionId) {
      List<PageComponent> result = pageComponentService.findAllBySectionId(sectionId);
      return ResponseEntity.ok(result);
   }
   
   @PostMapping("{sectionId}")
   public void createPageComponent(@PathVariable("sectionId") Long sectionId, @RequestBody PageComponentCreateDto pageComponentCreateDto) {
      pageComponentService.create(sectionId, pageComponentCreateDto);
   }
   
   @PostMapping("{currentPageComponentId}/next")
   public void nextPageComponent(@PathVariable("currentPageComponentId") Long currentPageComponentId, @RequestBody Long nextPageComponentId) {
      pageComponentService.affectNextPageComponent(currentPageComponentId, nextPageComponentId);
   }
}
