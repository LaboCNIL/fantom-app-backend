package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.ComponentType;

public record PageComponentCreateDto (String code, ComponentType type, Long parentId) {
}
