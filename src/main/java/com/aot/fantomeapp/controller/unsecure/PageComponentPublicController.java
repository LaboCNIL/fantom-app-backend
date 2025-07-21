package com.aot.fantomeapp.controller.unsecure;

import com.aot.fantomeapp.dto.PageComponentWithImageDto;
import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.service.PageComponentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/page-components")
@Slf4j
@RequiredArgsConstructor
public class PageComponentPublicController {

   private final PageComponentService pageComponentService;

   @GetMapping("section/{sectionId}/root")
   public ResponseEntity<PageComponentWithImageDto> getRootPageComponentBySectionId(
         @PathVariable("sectionId") Long sectionId, @RequestHeader("X-Devices") List<Device> devices) {
      log.debug("getRootPageComponentBySectionId");
      log.debug("Devices : {}", devices);
      PageComponentWithImageDto result = pageComponentService.findRootBySectionId(sectionId, devices);
      return ResponseEntity.ok(result);
   }

   @GetMapping("home-page-link-ids")
   public ResponseEntity<Map<String, Long>> getHomePageLinkIds() {
      log.debug("getHomePageLinkIds");
      Map<String, Long> result = pageComponentService.findHomePageLinkIds();
      return ResponseEntity.ok(result);
   }
}
