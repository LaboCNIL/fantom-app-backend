package com.aot.fantomeapp.model.enums;

import lombok.Getter;

@Getter
public enum CountryRegion {
   FR("France"),
   IE("Éire"),
   HU("Magyarország"),
   GR("Ελλάδα"),
   DK("Danmark"),
   PL("Polska"),
   PT("Portugal"),
   ES("España"),
   CT("Catalunya"),
   LU("Lëtzebuerg"),
   XX("International");
   
   private final String name;
   
   CountryRegion(String name) {
      this.name = name;
   }
}
