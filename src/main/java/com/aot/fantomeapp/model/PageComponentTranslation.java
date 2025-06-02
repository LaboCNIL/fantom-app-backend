package com.aot.fantomeapp.model;

import com.aot.fantomeapp.model.enums.Device;
import com.aot.fantomeapp.model.enums.Language;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "page_component_translation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageComponentTranslation extends BaseEntity {

   @Column
   private String firstTitle;

   @Column
   private String secondTitle;

   @Column
   private String shortDescription;

   @Column
   private String longDescription;

   @Column
   @Enumerated(EnumType.STRING)
   private Language language;

   @Column
   @Enumerated(EnumType.STRING)
   private Device device;

   @JsonIgnore
   @ManyToOne
   @JoinColumn(name = "page_component_id")
   private PageComponent pageComponent;
}
