package com.aot.fantomeapp.config;

import ch.qos.logback.classic.Level;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggerInitializer {

   @Value("${logging.level.ROOT}")
   private String rootLogLevel;

   @PostConstruct
   public void setRootLogger() {
      log.info("Logger initialized with level={}", rootLogLevel);
      ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(
         Logger.ROOT_LOGGER_NAME);
      try {
         root.setLevel(Level.valueOf(rootLogLevel));
      } catch (Exception e) {
         root.setLevel(Level.INFO);
      }
   }
}
