package com.aot.fantomeapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "foo")
@NamedEntityGraph(
   name = "full",
   attributeNodes = {
      @NamedAttributeNode("bars")
   }
)
@Data
public class Foo {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id; // La primary key s'appelle "id"
   
   private String name;
   
   @Column(name = "attribut_camel_case")
   private String attributCamelCase;
   
   @OneToMany(mappedBy = "foo")
   private List<Bar> bars;
   
}
