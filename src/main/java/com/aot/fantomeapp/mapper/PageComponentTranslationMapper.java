package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.PageComponentTranslationDto;
import com.aot.fantomeapp.dto.PageComponentTranslationWithImageDto;
import com.aot.fantomeapp.model.PageComponentTranslationWithImage;
import com.aot.fantomeapp.model.PageComponentTranslationLight;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PageComponentTranslationMapper {

   @Mapping(source = "pageComponent.id", target = "pageComponentId")
   PageComponentTranslationDto toDtoLight(PageComponentTranslationLight entity);

   @Mapping(source = "pageComponent.id", target = "pageComponentId")
   PageComponentTranslationWithImageDto toDtoWithImage(PageComponentTranslationWithImage entity);
}
