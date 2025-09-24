package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponentLight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageComponentLightRepository extends JpaRepository<PageComponentLight, Long> {

   // List<PageComponentLight> findAllBySectionId(Long sectionId);
   @Query("""
      SELECT p 
      FROM PageComponentLight p 
         LEFT JOIN FETCH p.section
         LEFT JOIN FETCH p.parent
         LEFT JOIN FETCH p.next
         LEFT JOIN FETCH p.modal
         LEFT JOIN FETCH p.children
      WHERE p.section.id = :sectionId
   """)
   List<PageComponentLight> findAllBySectionId(@Param("sectionId") Long sectionId);

   @Query("""
      SELECT p 
      FROM PageComponentLight p 
         LEFT JOIN FETCH p.translations
      WHERE p IN :pageComponents
   """)
   List<PageComponentLight> findAllWithTranslations(
         @Param("pageComponents") List<PageComponentLight> pageComponents);

   @Query("""
      SELECT p.code, p.id 
      FROM PageComponentLight p 
      WHERE p.code in :codes
      """)
   List<Pair<String, Long>> findIdByCodes(@Param("codes") List<String> codes);
}
