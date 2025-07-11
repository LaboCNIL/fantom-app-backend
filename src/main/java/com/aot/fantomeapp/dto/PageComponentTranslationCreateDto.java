package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.CountryRegion;

import java.util.List;

public record PageComponentTranslationCreateDto(CountryRegion countryRegion, List<Device> devices, String firstTitle, String secondTitle,
                                                String description, String image) {
}
