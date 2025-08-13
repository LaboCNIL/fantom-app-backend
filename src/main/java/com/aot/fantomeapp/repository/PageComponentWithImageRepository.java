package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponentWithImage;
import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import com.aot.fantomeapp.model.enums.TranslationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageComponentWithImageRepository extends JpaRepository<PageComponentWithImage, Long> {
   
   @Query("""
      SELECT p 
      FROM PageComponentWithImage p 
         LEFT JOIN FETCH p.section
         LEFT JOIN FETCH p.children
      WHERE p.section.id = :sectionId
         AND p.status = :status
   """)
   List<PageComponentWithImage> findAllBySectionIdAndStatus(
         @Param("sectionId") Long sectionId, 
         @Param("status") ComponentStatus status);
      
   
   @Query("""
      SELECT p 
      FROM PageComponentWithImage p 
         LEFT JOIN FETCH p.translations t
      WHERE p IN :pageComponents
         AND t.status = :status
   """)
   List<PageComponentWithImage> findAllWithTranslations(
         @Param("pageComponents") List<PageComponentWithImage> pageComponents,
         @Param("status") TranslationStatus status);

   Optional<PageComponentWithImage> findByCode(String code);
   
}
