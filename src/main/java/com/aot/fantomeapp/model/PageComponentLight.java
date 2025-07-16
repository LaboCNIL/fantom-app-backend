package com.aot.fantomeapp.model;

import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.springframework.data.annotation.Immutable;

@Entity
@Table(name = "page_component")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
public class PageComponentLight {
   
   @Id
   protected Long id;

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

   @ManyToOne
   @JoinColumn(name = "parent_page_component_id")
   private PageComponentLight parent;

   @OneToMany(mappedBy = "parent")
   private List<PageComponentLight> children;
   
   @OneToOne
   @JoinColumn(name = "next_page_component_id")
   private PageComponentLight next;
   
   @OneToMany(mappedBy = "pageComponent")
   private List<PageComponentTranslationLight> translations;
}
