package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.CountryRegion;

public record PageComponentTranslationDto(Long id, String firstTitle, String secondTitle, String description,
                                          CountryRegion countryRegion, Device device, String image, Long pageComponentId) {
}
