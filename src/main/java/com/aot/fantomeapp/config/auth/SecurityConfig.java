package com.aot.fantomeapp.config.auth;

import com.aot.fantomeapp.config.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
class SecurityConfig {

   private final AppProperties appProperties;

   private final Converter<Jwt, ConnectedUser> jwtConverter;

   @Value("#{'${app.security.unsecure.paths:}'.split(',')}")
   private String[] unsecuredPathUri;

   @Value("#{'${app.security.cors.allowed-origins:}'.split(',')}")
   private String[] corsAllowedOrigins;

   @Bean
   protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
      return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
   }

   @Bean
   public SecurityFilterChain configure(HttpSecurity http) throws Exception {

      http
         .cors(cors -> cors.configurationSource(corsConfigurationSource()))
         .csrf(AbstractHttpConfigurer::disable)
         .formLogin(AbstractHttpConfigurer::disable)
         .httpBasic(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(
            request -> request
               .requestMatchers(unsecuredPathUri).permitAll()
               .anyRequest().authenticated()
         )
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

         .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

      return http.build();
   }

   @Bean
   public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOrigins(List.of(corsAllowedOrigins));
      config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
      config.setAllowedHeaders(List.of("*"));
      config.setAllowCredentials(true);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);

      return source;
   }
}
