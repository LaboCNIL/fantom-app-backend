package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.PageComponentDto;
import com.aot.fantomeapp.dto.PageComponentWithImageDto;
import com.aot.fantomeapp.model.PageComponentWithImage;
import com.aot.fantomeapp.model.PageComponentLight;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PageComponentTranslationMapper.class})
public interface PageComponentMapper {

   @Mapping(source = "parent.id", target = "parentId")
   @Mapping(source = "section.id", target = "sectionId")
   PageComponentDto toDtoLight(PageComponentLight entity);

   @Mapping(source = "parent.id", target = "parentId")
   @Mapping(source = "section.id", target = "sectionId")
   List<PageComponentDto> toDtoLight(List<PageComponentLight> entities);
   
   @Mapping(source = "parent.id", target = "parentId")
   @Mapping(source = "section.id", target = "sectionId")
   @Mapping(source = "next.id", target = "nextId")
   @Mapping(source = "children", target = "childrenIdList", qualifiedByName = "toChildrenIdList")
   PageComponentWithImageDto toDtoWithImage(PageComponentWithImage entity);

   @Mapping(source = "parent.id", target = "parentId")
   @Mapping(source = "section.id", target = "sectionId")
   @Mapping(source = "next.id", target = "nextId")
   @Mapping(source = "children", target = "childrenIdList", qualifiedByName = "toChildrenIdList")
   List<PageComponentWithImageDto> toDtoWithImage(List<PageComponentWithImage> entities);

   @Named("toChildrenIdList")
   default List<Long> toChildrenIdList(List<PageComponentWithImage> children) {
      return children.stream()
            .map(PageComponentWithImage::getId)
            .collect(Collectors.toList());
   }
}
