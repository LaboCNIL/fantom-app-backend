package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.dto.PageComponentTranslationCreateDto;
import com.aot.fantomeapp.service.PageComponentTranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page-component-translations")
@Slf4j
@RequiredArgsConstructor
public class PageComponentTranslationController {

   private final PageComponentTranslationService pageComponentTranslationService;

   @PostMapping("{pageComponentId}")
   public void createPageComponentTranslation(@PathVariable("pageComponentId") Long pageComponentId, @RequestBody PageComponentTranslationCreateDto dto) {
      log.debug("createPageComponentTranslation");
      pageComponentTranslationService.create(pageComponentId, dto);
   }
}
