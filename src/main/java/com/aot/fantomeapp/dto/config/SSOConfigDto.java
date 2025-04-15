package com.aot.fantomeapp.dto.config;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SSOConfigDto {

   private String issuer;

   private String clientID;

}
