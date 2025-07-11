package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.model.enums.Device;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class DeviceListConverter implements AttributeConverter<List<Device>, String> {

   private static final String SEPARATOR = ",";

   @Override
   public String convertToDatabaseColumn(List<Device> devices) {
      return devices != null ? devices.stream()
         .map(Device::name)
         .collect(Collectors.joining(SEPARATOR)) : "";
   }

   @Override
   public List<Device> convertToEntityAttribute(String dbData) {
      return dbData != null && !dbData.isEmpty() ?
         Arrays.stream(dbData.split(SEPARATOR))
            .map(Device::valueOf)
            .collect(Collectors.toList()) : List.of();
   }
}
