package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.PageComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageComponentRepository extends JpaRepository<PageComponent, Long> {
}
