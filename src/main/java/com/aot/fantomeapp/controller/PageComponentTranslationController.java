package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.dto.PageComponentTranslationCreateUpdateDto;
import com.aot.fantomeapp.dto.PageComponentTranslationWithImageDto;
import com.aot.fantomeapp.service.PageComponentTranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page-component-translations")
@Slf4j
@RequiredArgsConstructor
public class PageComponentTranslationController {

   private final PageComponentTranslationService pageComponentTranslationService;

   @GetMapping("{pageComponentTranslationId}")
   public ResponseEntity<PageComponentTranslationWithImageDto> getPageComponentTranslation(@PathVariable("pageComponentTranslationId") Long pageComponentTranslationId) {
      log.debug("getPageComponentTranslation");
      PageComponentTranslationWithImageDto result = pageComponentTranslationService.findByIdWithImage(pageComponentTranslationId);
      return ResponseEntity.ok(result);
   }
   
   @PostMapping("{pageComponentId}")
   public void createPageComponentTranslation(@PathVariable("pageComponentId") Long pageComponentId, @RequestBody PageComponentTranslationCreateUpdateDto dto) {
      log.debug("createPageComponentTranslation");
      pageComponentTranslationService.create(pageComponentId, dto);
   }

   @PutMapping("{pageComponentTranslationId}")
   public void updatePageComponentTranslation(@PathVariable("pageComponentTranslationId") Long pageComponentTranslationId, @RequestBody PageComponentTranslationCreateUpdateDto dto) {
      log.debug("updatePageComponentTranslation");
      pageComponentTranslationService.update(pageComponentTranslationId, dto);
   }

   @DeleteMapping("{pageComponentTranslationId}")
   public void deletePageComponentTranslation(@PathVariable("pageComponentTranslationId") Long pageComponentTranslationId) {
      log.debug("deletePageComponentTranslation");
      pageComponentTranslationService.delete(pageComponentTranslationId);
   }
}
