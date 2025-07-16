package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponentTranslationLight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageComponentTranslationLightRepository extends JpaRepository<PageComponentTranslationLight, Long> {
}
