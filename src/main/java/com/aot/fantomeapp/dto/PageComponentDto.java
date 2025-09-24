package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;

import java.util.List;

public record PageComponentDto(Long id, String code, ComponentType type, ComponentStatus status, Integer position, 
                               Long parentId, Long sectionId, PageComponentDto next, PageComponentDto modal, List<PageComponentTranslationDto> translations,
                               List<PageComponentDto> children) {
}
