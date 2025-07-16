package com.aot.fantomeapp.controller.unsecure;

import com.aot.fantomeapp.dto.PageComponentWithImageDto;
import com.aot.fantomeapp.service.PageComponentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/page-components")
@Slf4j
@RequiredArgsConstructor
public class PageComponentPublicController {

   private final PageComponentService pageComponentService;

   @GetMapping("section/{sectionId}/root")
   public ResponseEntity<PageComponentWithImageDto> getRootPageComponentBySectionId(@PathVariable("sectionId") Long sectionId) {
      log.debug("getRootPageComponentBySectionId");
      PageComponentWithImageDto result = pageComponentService.findRootBySectionId(sectionId);
      return ResponseEntity.ok(result);
   }
}
