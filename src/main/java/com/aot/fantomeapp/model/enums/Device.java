package com.aot.fantomeapp.model.enums;

import java.util.List;

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

   /**
    * Retourne le nom du premier device trouvé dans la liste, selon la priorité : IOS, ANDROID, WEB.
    */
   public static String getMainDevice(List<Device> devices) {
      if (devices == null || devices.isEmpty()) {
         return null;
      }
      if (devices.contains(IOS)) {
         return IOS.name();
      }
      if (devices.contains(ANDROID)) {
         return ANDROID.name();
      }
      return WEB.name();
   }
}
