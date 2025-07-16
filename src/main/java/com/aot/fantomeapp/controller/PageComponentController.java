package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.dto.PageComponentCreateDto;
import com.aot.fantomeapp.dto.PageComponentDto;
import com.aot.fantomeapp.dto.PageComponentUpdateDto;
import com.aot.fantomeapp.service.PageComponentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page-components")
@Slf4j
@RequiredArgsConstructor
public class PageComponentController {
   
   private final PageComponentService pageComponentService;

   @GetMapping("{pageComponentId}")
   public ResponseEntity<PageComponentDto> getPageComponentById(@PathVariable("pageComponentId") Long pageComponentId) {
      log.debug("getPageComponentById");
      PageComponentDto result = pageComponentService.findById(pageComponentId);
      return ResponseEntity.ok(result);
   }
   
   @GetMapping("section/{sectionId}")
   public ResponseEntity<List<PageComponentDto>> getPageComponentsBySectionId(@PathVariable("sectionId") Long sectionId) {
      log.debug("getPageComponentsBySectionId");
      List<PageComponentDto> result = pageComponentService.findAllLightBySectionId(sectionId);
      return ResponseEntity.ok(result);
   }

   @PostMapping("{sectionId}")
   public void createPageComponent(@PathVariable("sectionId") Long sectionId, @RequestBody PageComponentCreateDto dto) {
      log.debug("createPageComponent");
      pageComponentService.create(sectionId, dto);
   }

   @PutMapping("{currentPageComponentId}/next")
   public void nextPageComponent(@PathVariable("currentPageComponentId") Long currentPageComponentId, @RequestBody Long nextPageComponentId) {
      log.debug("nextPageComponent");
      pageComponentService.affectNextPageComponent(currentPageComponentId, nextPageComponentId);
   }

   @PutMapping("{pageComponentId}")
   public void updatePageComponent(@PathVariable("pageComponentId") Long pageComponentId, @RequestBody PageComponentUpdateDto dto) {
      log.debug("updatePageComponent");
      pageComponentService.update(pageComponentId, dto);
   }

   @DeleteMapping("{pageComponentId}")
   public void deletePageComponent(@PathVariable("pageComponentId") Long pageComponentId) {
      log.debug("deletePageComponent");
      pageComponentService.delete(pageComponentId);
   }
}
