package com.aot.fantomeapp.config.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

@ToString
@Getter
@Setter
public class ConnectedUser extends JwtAuthenticationToken {
   private String id;
   private String name;
   private String email;
   private Collection<? extends GrantedAuthority> roles;

   public ConnectedUser(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
      super(jwt, authorities);
   }

}
