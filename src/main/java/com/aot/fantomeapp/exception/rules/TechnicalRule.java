package com.aot.fantomeapp.exception.rules;

import lombok.Getter;

@Getter
public enum TechnicalRule {

   // Add rules here : S3_0001("S3_0001", "Le S3 n'est pas connectée"),
   FOO_0001("FOO_0001", "Objet foo n'existe pas"),
   ;

   private final String name;
   private final String message;

   TechnicalRule(final String name, final String message) {
      this.name = name;
      this.message = message;
   }

   @Override
   public String toString() {
      return String.format("%s - %s", this.getName(), this.getMessage());
   }

   public String toString(final Object... params) {
      return String.format("%s - %s", this.getName(), String.format(this.getMessage(), params));
   }
}
