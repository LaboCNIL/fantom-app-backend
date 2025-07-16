package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.CountryRegion;
import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.TranslationStatus;

import java.util.List;

public record PageComponentTranslationWithImageDto(Long id, TranslationStatus status, String firstTitle, String secondTitle, String description,
                                                   CountryRegion countryRegion, List<Device> devices, String image, Long pageComponentId) {
}
