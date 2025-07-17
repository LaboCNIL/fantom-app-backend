package com.aot.fantomeapp.model;

import com.aot.fantomeapp.mapper.DeviceListConverter;
import com.aot.fantomeapp.model.enums.CountryRegion;
import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.TranslationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "page_component_translation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageComponentTranslationWithImage extends BaseEntity {

   @Column
   @Enumerated(EnumType.STRING)
   private TranslationStatus status;
   
   @Column
   private String firstTitle;

   @Column
   private String secondTitle;

   @Column
   private String description;

   @Column
   @Enumerated(EnumType.STRING)
   private CountryRegion countryRegion;

   @Convert(converter = DeviceListConverter.class)
   @Column(name = "device")
   private List<Device> devices;

   @Column
   //@Basic(fetch = FetchType.LAZY)
   private String image;
   
   @ManyToOne
   @JoinColumn(name = "page_component_id")
   private PageComponentWithImage pageComponent;
}
