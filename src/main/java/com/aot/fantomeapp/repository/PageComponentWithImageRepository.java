package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponentWithImage;
import com.aot.fantomeapp.model.enums.ComponentStatus;
import com.aot.fantomeapp.model.enums.ComponentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageComponentWithImageRepository extends JpaRepository<PageComponentWithImage, Long> {
   
   List<PageComponentWithImage> findAllBySectionIdAndStatus(Long sectionId, ComponentStatus status);

   Optional<PageComponentWithImage> findByCode(String code);
   
}
