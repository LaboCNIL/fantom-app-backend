package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.FooDto;
import com.aot.fantomeapp.model.Foo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FooMapper {

   public static FooDto toDto(Foo foo) {
      FooDto dto = toReducedDto(foo);
      dto.setBars(BarMapper.toDtoList(foo.getBars()));

      return dto;
   }

   public static FooDto toReducedDto(Foo foo) {
      FooDto dto = new FooDto();

      dto.setId(foo.getId());
      dto.setName(foo.getName());
      dto.setAttributCamelCase(foo.getAttributCamelCase());

      return dto;
   }

   public static Collection<FooDto> collectionToDto(Collection<Foo> bars, Collector<FooDto, ?, ? extends Collection<FooDto>> collector) {
      return bars.stream()
         .map(FooMapper::toReducedDto)
         .collect(collector);
   }

   public static List<FooDto> toDtoList(Collection<Foo> bars) {
      return (List<FooDto>) collectionToDto(bars, Collectors.toList());
   }
}
