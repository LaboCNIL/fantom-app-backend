package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.BarDto;
import com.aot.fantomeapp.model.Bar;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BarMapper {

   public static BarDto toDto(Bar bar) {
      BarDto dto = new BarDto();

      dto.setId(bar.getId());
      dto.setName(bar.getName());

      return dto;
   }

   public static Collection<BarDto> collectionToDto(Collection<Bar> bars, Collector<BarDto, ?, ? extends Collection<BarDto>> collector) {
      return bars.stream()
         .map(BarMapper::toDto)
         .collect(collector);
   }

   public static List<BarDto> toDtoList(Collection<Bar> bars) {
      return (List<BarDto>) collectionToDto(bars, Collectors.toList());
   }

}
