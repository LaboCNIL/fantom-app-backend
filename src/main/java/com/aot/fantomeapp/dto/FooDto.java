package com.aot.fantomeapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class FooDto {
   
   private Long id;
   
   private String name;
   
   private String attributCamelCase;
   
   private List<BarDto> bars;

}
