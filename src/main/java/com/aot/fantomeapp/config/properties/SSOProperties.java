package com.aot.fantomeapp.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class SSOProperties {

   private String issuer;

   private String clientID;

}
