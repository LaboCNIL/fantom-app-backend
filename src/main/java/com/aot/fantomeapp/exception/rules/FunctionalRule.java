package com.aot.fantomeapp.exception.rules;

import lombok.Getter;

@Getter
public enum FunctionalRule {

   // Add rules here : A_0001("A_0001", "Cette fonctionnalité n'est pas disponible"),
   FOO_0001("FOO_0001", "Ce nom est interdit"),
   ;

   private final String name;
   private final String message;

   FunctionalRule(final String name, final String message) {
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
