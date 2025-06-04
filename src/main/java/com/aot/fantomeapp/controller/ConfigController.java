package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.config.properties.AppProperties;
import com.aot.fantomeapp.dto.config.AppConfigDto;
import com.aot.fantomeapp.mapper.config.AppConfigMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ConfigController {

   private final AppProperties appProperties;

   @GetMapping("/config")
   public ResponseEntity<AppConfigDto> getConfig() {
      log.debug("getConfig");
      return ResponseEntity.ok(AppConfigMapper.appPropertiesToDto(appProperties));
   }
}
