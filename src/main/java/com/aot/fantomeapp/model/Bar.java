package com.aot.fantomeapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "bar")
@Data
public class Bar {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id; // La primary key s'appelle "id"
   
   private String name;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "foo_id")
   private Foo foo; // On nomme la foreign key ainsi : <nom>_id

}
