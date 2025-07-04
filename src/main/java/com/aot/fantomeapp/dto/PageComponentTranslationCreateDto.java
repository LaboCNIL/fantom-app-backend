package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.Language;

public record PageComponentTranslationCreateDto(Language language, Device device, String firstTitle, String secondTitle,
                                                String shortDescription, String image) {
}
