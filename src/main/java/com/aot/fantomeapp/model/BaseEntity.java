package com.aot.fantomeapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   protected Long id;

   @Column(name = "created_at")
   private Instant createdAt;

   @Column(name = "updated_at")
   private Instant updatedAt;

   @PrePersist
   protected void onCreate() {
      this.createdAt = Instant.now();
      this.updatedAt = Instant.now();
   }

   @PreUpdate
   protected void onUpdate() {
      this.updatedAt = Instant.now();
   }
}
