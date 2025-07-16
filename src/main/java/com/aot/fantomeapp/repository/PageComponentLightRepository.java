package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponentLight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageComponentLightRepository extends JpaRepository<PageComponentLight, Long> {

   List<PageComponentLight> findAllBySectionId(Long sectionId);
   }
