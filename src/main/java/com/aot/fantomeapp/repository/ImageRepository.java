package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    Optional<Image> findByHash(String hash);
} 