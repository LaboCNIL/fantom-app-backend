package com.aot.fantomeapp.model.enums;

import lombok.Getter;

@Getter
public enum Language {
   CA("CATALAN"),
   DA("DANISH"),
   EL("GREEK"),
   EN("ENGLISH"),
   ES("SPANISH"),
   FR("FRENCH"),
   HU("HUNGARIAN"),
   PL("POLISH"),
   PT("PORTUGUESE");
   
   private final String value;
   
   Language(String code) {
      this.value = code;
   }
}
