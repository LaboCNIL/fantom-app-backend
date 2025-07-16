package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;

import java.util.List;

public record PageComponentWithImageDto(Long id, String code, ComponentType type, ComponentStatus status, Integer position,
                                        Long parentId, Long sectionId, PageComponentWithImageDto next, List<PageComponentTranslationWithImageDto> translations,
                                        List<PageComponentWithImageDto> children) {
}
