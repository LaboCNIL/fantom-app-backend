package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.Language;

public record PageComponentTranslationDto(Long id, String firstTitle, String secondTitle, String description,
                                          Language language, Device device, String image, Long pageComponentId) {
}
