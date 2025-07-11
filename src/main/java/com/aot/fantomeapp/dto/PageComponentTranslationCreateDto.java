package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.CountryRegion;

public record PageComponentTranslationCreateDto(CountryRegion countryRegion, Device device, String firstTitle, String secondTitle,
                                                String description, String image) {
}
