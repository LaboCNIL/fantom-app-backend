package com.aot.fantomeapp.controller.unsecure;

import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.service.PageComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/page-components")
@RequiredArgsConstructor
public class PageComponentPublicController {

   private final PageComponentService pageComponentService;

   @GetMapping("{sectionId}")
   public ResponseEntity<List<PageComponent>> getPageComponents(@PathVariable("sectionId") Long sectionId) {
      List<PageComponent> result = pageComponentService.findAllBySectionId(sectionId);
      return ResponseEntity.ok(result);
   }
}
