package com.aot.fantomeapp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
   private String id;
   private String email;
   private String name;
   private List<String> roles;
   private List<String> languages;
}
