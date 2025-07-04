package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.model.enums.ComponentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageComponentRepository extends JpaRepository<PageComponent, Long> {

   List<PageComponent> findAllBySectionId(Long sectionId);
   
   List<PageComponent> findAllBySectionIdAndStatus(Long sectionId, ComponentStatus status);
}
