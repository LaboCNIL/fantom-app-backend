package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.PageComponentDto;
import com.aot.fantomeapp.model.PageComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageComponentMapper {

   @Mapping(source = "parent.id", target = "parentId")
   PageComponentDto toDto(PageComponent entity);

   @Mapping(source = "parent.id", target = "parentId")
   List<PageComponentDto> toDto(List<PageComponent> entities);
}
