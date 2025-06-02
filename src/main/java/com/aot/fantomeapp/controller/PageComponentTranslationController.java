package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.dto.PageComponentTranslationCreateDto;
import com.aot.fantomeapp.service.PageComponentTranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page-component-translations")
@RequiredArgsConstructor
public class PageComponentTranslationController {

   private final PageComponentTranslationService pageComponentTranslationService;

   @PostMapping("{pageComponentId}")
   public void createPageComponentTranslation(@PathVariable("pageComponentId") Long pageComponentId, @RequestBody PageComponentTranslationCreateDto dto) {
      pageComponentTranslationService.create(pageComponentId, dto);
   }
}
