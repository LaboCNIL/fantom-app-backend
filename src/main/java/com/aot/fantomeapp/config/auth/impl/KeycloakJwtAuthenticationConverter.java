package com.aot.fantomeapp.config.auth.impl;

import com.aot.fantomeapp.config.auth.ConnectedUser;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Component
@Slf4j
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, ConnectedUser> {

   @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
   private String clientID;

   public static final String PREFERRED_USERNAME_CLAIM = "preferred_username";
   public static final String SUB_CLAIM = "sub";
   public static final String EMAIL_CLAIM = "email";
   public static final String RESOURCE_ACCESS_CLAIM = "resource_access";
   public static final String ROLE_CLAIM = "roles";
   public static final String LANGUAGE_CLAIM = "language";

   @Override
   public ConnectedUser convert(Jwt jwt) {
      Collection<? extends GrantedAuthority> authorities = extractResourceRoles(jwt);
      ConnectedUser user = new ConnectedUser(jwt, authorities);

      // Récupérer les champs du JWT pour les transmettre au front.
      String id = jwt.getClaimAsString(SUB_CLAIM);
      String name = jwt.getClaimAsString(PREFERRED_USERNAME_CLAIM);
      String email = jwt.getClaimAsString(EMAIL_CLAIM);

      user.setId(id);
      user.setName(name);
      user.setEmail(email);
      user.setRoles(authorities);
      user.setLanguages(jwt.getClaimAsStringList(LANGUAGE_CLAIM).stream()
            .map(x -> x.toUpperCase()).collect(Collectors.toList()));
      return user;
   }

   private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
      Map<String, Object> resourceAccess = new HashMap<>(jwt.getClaimAsMap(RESOURCE_ACCESS_CLAIM));
      List<String> resourceRoles = new ArrayList<>();

      if (resourceAccess.containsKey(clientID)) {
         @SuppressWarnings("unchecked") var resource = (Map<String, List<String>>) resourceAccess.get(clientID);
         resourceRoles.addAll(resource.getOrDefault(ROLE_CLAIM, List.of()));
      }
      return resourceRoles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(toSet());
   }
}
