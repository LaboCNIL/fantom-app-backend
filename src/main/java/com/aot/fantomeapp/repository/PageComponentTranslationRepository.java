package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponentTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageComponentTranslationRepository extends JpaRepository<PageComponentTranslation, Long> {
}
