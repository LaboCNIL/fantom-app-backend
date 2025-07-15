package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;

public record PageComponentUpdateDto(String code, ComponentType type, Long parentId, Integer position, Long sectionId, ComponentStatus status) {
}
