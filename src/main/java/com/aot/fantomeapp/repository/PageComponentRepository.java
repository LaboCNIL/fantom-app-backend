package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponent;
import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageComponentRepository extends JpaRepository<PageComponent, Long> {

   List<PageComponent> findAllBySectionId(Long sectionId);
   
   List<PageComponent> findAllBySectionIdAndStatusAndType(Long sectionId, ComponentStatus status, ComponentType type);
}
