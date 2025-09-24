package com.aot.fantomeapp.model;

import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import java.util.List;

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

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "section_id")
   private Section section;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_page_component_id")
   private PageComponentLight parent;

   @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
   private List<PageComponentLight> children;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "next_page_component_id")
   private PageComponentLight next;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "modal_page_component_id")
   private PageComponentLight modal;

   @OneToMany(mappedBy = "pageComponent", fetch = FetchType.LAZY)
   private List<PageComponentTranslationLight> translations;
}
