package com.aot.fantomeapp.model;

import com.aot.fantomeapp.model.enums.Language;
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
   private String content;

   @Enumerated(EnumType.STRING)
   private Language language;

   @Column
   private String device;

   @Column
   private String status;

   @ManyToOne
   @JoinColumn(name = "page_component_id")
   private PageComponent pageComponent;
}
