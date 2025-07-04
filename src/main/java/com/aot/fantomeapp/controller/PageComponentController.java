package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.dto.PageComponentCreateDto;
import com.aot.fantomeapp.dto.PageComponentDto;
import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.service.PageComponentService;
import jakarta.annotation.Nullable;
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
   
   @GetMapping("section/{sectionId}")
   public ResponseEntity<List<PageComponentDto>> getPageComponentsBySectionId(@PathVariable("sectionId") Long sectionId) {
      log.debug("getPageComponentsBySectionId");
      List<PageComponentDto> result = pageComponentService.findAllBySectionId(sectionId);
      return ResponseEntity.ok(result);
   }
   
   @PostMapping("{sectionId}")
   public void createPageComponent(@PathVariable("sectionId") Long sectionId, @RequestBody PageComponentCreateDto pageComponentCreateDto) {
      log.debug("createPageComponent");
      pageComponentService.create(sectionId, pageComponentCreateDto);
   }
   
   @PutMapping("{currentPageComponentId}/next")
   public void nextPageComponent(@PathVariable("currentPageComponentId") Long currentPageComponentId, @RequestBody Long nextPageComponentId) {
      log.debug("nextPageComponent");
      pageComponentService.affectNextPageComponent(currentPageComponentId, nextPageComponentId);
   }
   
   @PutMapping("{currentPageComponentId}/parent")
   public void updateParentPageComponent(@PathVariable("currentPageComponentId") Long currentPageComponentId, @RequestBody Long parentPageComponentId) {
      log.debug("updateParentPageComponent");
      pageComponentService.updateParentPageComponent(currentPageComponentId, parentPageComponentId);
   }
}
