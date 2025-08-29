package com.aot.fantomeapp.dto.config;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppConfigDto {
   private String version;

   private String urlApp;

   private SSOConfigDto sso;

}
