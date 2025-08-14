package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.PageComponentTranslationDto;
import com.aot.fantomeapp.dto.PageComponentTranslationWithImageDto;
import com.aot.fantomeapp.model.Image;
import com.aot.fantomeapp.model.PageComponentTranslationWithImage;
import com.aot.fantomeapp.model.PageComponentTranslationLight;
import com.aot.fantomeapp.service.ImageService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PageComponentTranslationMapper {

    public static final String IMAGE_URL = "/api/public/images/";

    @Autowired
    protected ImageService imageService;

    @Mapping(source = "pageComponent.id", target = "pageComponentId")
    public abstract PageComponentTranslationDto toDtoLight(PageComponentTranslationLight entity);

    @Mapping(source = "pageComponent.id", target = "pageComponentId")
    @Mapping(source = "imageId", target = "image", qualifiedByName = "imageIdToImageUrl")
    @Named("toTranslationsWithImageUrl")
    public abstract PageComponentTranslationWithImageDto toDtoWithImage(PageComponentTranslationWithImage entity);

    @Mapping(source = "pageComponent.id", target = "pageComponentId")
    @Mapping(source = "image", target = "image", qualifiedByName = "imageToBase64")
    @Named("toTranslationsWithImageBase64")
    public abstract PageComponentTranslationWithImageDto toDtoWithImageBase64(PageComponentTranslationWithImage entity);


    @Named("imageIdToImageUrl")
    protected String imageIdToImageUrl(Long imageId) {
        if (imageId == null) {
            return null;
        }
        return IMAGE_URL + imageId;
    }

    @Named("imageToBase64")
   protected String imageToBase64(Image image) {
      return imageService.convertImageToBase64(image);
   }
}
