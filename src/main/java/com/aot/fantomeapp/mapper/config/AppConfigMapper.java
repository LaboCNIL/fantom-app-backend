package com.aot.fantomeapp.mapper.config;

import com.aot.fantomeapp.config.properties.AppProperties;
import com.aot.fantomeapp.config.properties.SSOProperties;
import com.aot.fantomeapp.dto.config.AppConfigDto;
import com.aot.fantomeapp.dto.config.SSOConfigDto;

public class AppConfigMapper {

   public static AppConfigDto appPropertiesToDto(AppProperties properties) {
      return AppConfigDto.builder()
         .version(properties.getVersion())
         
         .sso(ssoConfigToDto(properties.getSso()))
         
         .build();
   }
   

   private static SSOConfigDto ssoConfigToDto(SSOProperties properties) {
      return SSOConfigDto.builder()
         .issuer(properties.getIssuer())
         .clientID(properties.getClientID())
         .build();
   }


}
