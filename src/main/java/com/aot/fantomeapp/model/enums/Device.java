package com.aot.fantomeapp.model.enums;

import lombok.Getter;

@Getter
public enum Device {
   ANDROID("Android"),
   IOS("iOS"),
   WEB("Web");

   private final String code;

   Device(String code) {
      this.code = code;
   }
}
