package com.aot.fantomeapp.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(value = "app")
@Getter
@Setter
public class AppProperties {

   private String version;

   @NestedConfigurationProperty
   private SSOProperties sso;
}
