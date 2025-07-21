package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.PageComponentTranslationDto;
import com.aot.fantomeapp.dto.PageComponentTranslationWithImageDto;
import com.aot.fantomeapp.model.Image;
import com.aot.fantomeapp.model.PageComponentTranslationWithImage;
import com.aot.fantomeapp.model.PageComponentTranslationLight;
import com.aot.fantomeapp.service.ImageService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PageComponentTranslationMapper {

   @Autowired
   protected ImageService imageService;

   @Mapping(source = "pageComponent.id", target = "pageComponentId")
   public abstract PageComponentTranslationDto toDtoLight(PageComponentTranslationLight entity);

   @Mapping(source = "pageComponent.id", target = "pageComponentId")
   @Mapping(source = "image", target = "image", qualifiedByName = "imageToBase64")
   public abstract PageComponentTranslationWithImageDto toDtoWithImage(PageComponentTranslationWithImage entity);

   @org.mapstruct.Named("imageToBase64")
   protected String imageToBase64(Image image) {
      return imageService.convertImageToBase64(image);
   }
}
