package com.aot.fantomeapp.dto;

import com.aot.fantomeapp.model.enums.CountryRegion;
import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.TranslationStatus;

import java.util.List;

public record PageComponentTranslationDto(Long id, CountryRegion countryRegion, List<Device> devices, TranslationStatus status, 
                                          String firstTitle, String secondTitle, String description, Long pageComponentId) {
}
