package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponentTranslationWithImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageComponentTranslationWithImageRepository extends JpaRepository<PageComponentTranslationWithImage, Long> {
}
