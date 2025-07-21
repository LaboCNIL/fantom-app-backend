package com.aot.fantomeapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {

   @Column(name = "mime_type", nullable = false, length = 50)
   private String mimeType;

   @Column(name = "content", nullable = false)
   private byte[] content;

   @Column(name = "hash", nullable = false, unique = true)
   private String hash;
} 