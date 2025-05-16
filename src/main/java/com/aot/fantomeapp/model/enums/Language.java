package com.aot.fantomeapp.model.enums;

import lombok.Getter;

@Getter
public enum Language {
   CATALAN("CA"),
   DANISH("DA"),
   GREEK("EL"),
   ENGLISH("EN"),
   SPANISH("ES"),
   FRENCH("FR"),
   HUNGARIAN("HU"),
   POLISH("PL"),
   PORTUGUESE("PT");
   
   private final String code;
   
   Language(String code) {
      this.code = code;
   }
}
