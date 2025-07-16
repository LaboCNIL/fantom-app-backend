package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.PageComponentTranslationDto;
import com.aot.fantomeapp.dto.PageComponentTranslationWithImageDto;
import com.aot.fantomeapp.model.PageComponentTranslation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PageComponentTranslationMapper {

   @Mapping(source = "pageComponent.id", target = "pageComponentId")
   PageComponentTranslationDto toDto(PageComponentTranslation entity);

   @Mapping(source = "pageComponent.id", target = "pageComponentId")
   PageComponentTranslationWithImageDto toDtoWithImage(PageComponentTranslation entity);
}
