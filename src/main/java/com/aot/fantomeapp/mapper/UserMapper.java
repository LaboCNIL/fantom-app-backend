package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.config.auth.ConnectedUser;
import com.aot.fantomeapp.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserMapper {

   public static UserDto connectedUserToDto(ConnectedUser connectedUser) {
      return UserDto.builder()
         .id(connectedUser.getId())
         .email(connectedUser.getEmail())
         .name(connectedUser.getName())
         .roles(grantedAuthorityToString(connectedUser.getRoles()))
         .build();
   }

   private static List<String> grantedAuthorityToString(Collection<? extends GrantedAuthority> roles) {
      return roles.stream().map(GrantedAuthority::getAuthority).toList();
   }

}
