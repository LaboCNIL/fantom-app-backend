package com.aot.fantomeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class FantomeAppApplication {
   public static void main(String[] args) {
      SpringApplication springApplication = new SpringApplication(FantomeAppApplication.class);
      springApplication.addListeners(new ApplicationPidFileWriter());
      springApplication.run(args);
   }
}
