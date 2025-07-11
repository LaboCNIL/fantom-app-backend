package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.CountryRegion;

import java.util.List;

public record PageComponentTranslationDto(Long id, String firstTitle, String secondTitle, String description,
                                          CountryRegion countryRegion, List<Device> devices, String image, Long pageComponentId) {
}
