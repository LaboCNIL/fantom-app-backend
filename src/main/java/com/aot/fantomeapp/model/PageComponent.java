package com.aot.fantomeapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "page_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageComponent extends BaseEntity {
   
   @Column
   private String code;

   @Column
   private String type;

   @Column
   private Integer position;

   @Column
   private String image;

   @OneToOne
   @JoinColumn(name = "section_id")
   private Section section;

   @OneToOne
   @JoinColumn(name = "previous_page_component_id")
   private PageComponent previous;

   @OneToOne
   @JoinColumn(name = "next_page_component_id")
   private PageComponent next;
}
