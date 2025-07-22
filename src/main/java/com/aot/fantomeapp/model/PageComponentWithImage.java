package com.aot.fantomeapp.model;

import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "page_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageComponentWithImage extends BaseEntity {
   
   @Column
   private String code;

   @Column
   @Enumerated(EnumType.STRING)
   private ComponentType type;

   @Column
   @Enumerated(EnumType.STRING)
   private ComponentStatus status;

   @Column
   private Integer position;
   
   @OneToOne
   @JoinColumn(name = "section_id")
   private Section section;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_page_component_id")
   private PageComponentWithImage parent;
   @Column(name = "parent_page_component_id", insertable = false, updatable = false)
   private Long parentId;

   //@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
   @OneToMany(mappedBy = "parent")
   @OrderBy("position ASC")
   private List<PageComponentWithImage> children;
   
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "next_page_component_id")
   private PageComponentWithImage next;
   @Column(name = "next_page_component_id", insertable = false, updatable = false)
   private Long nextId;
   
   @OneToMany(mappedBy = "pageComponent", fetch = FetchType.LAZY)
   private List<PageComponentTranslationWithImage> translations;
}
