package com.aot.fantomeapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;

@Configuration
public class ThymeleafConfig {

   @Bean
   public ClassLoaderTemplateResolver templateResolver() {
      ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

      resolver.setPrefix("templates/"); // Location of thymeleaf template
      resolver.setCacheable(true);
      resolver.setSuffix(".html"); // Template file extension
      resolver.setTemplateMode(TemplateMode.HTML);
      resolver.setCharacterEncoding(StandardCharsets.UTF_8.toString());

      return resolver;
   }

   @Bean
   public SpringTemplateEngine templateEngine() {
      SpringTemplateEngine engine = new SpringTemplateEngine();
      engine.setTemplateResolver(templateResolver());

      return engine;
   }
}
