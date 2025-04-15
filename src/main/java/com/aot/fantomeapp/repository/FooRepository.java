package com.aot.fantomeapp.repository;

import com.aot.fantomeapp.model.Foo;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {

   @Override
   @EntityGraph(value = "full")
   Optional<Foo> findById(@NonNull Long id);

   @Query("""
         SELECT f
         FROM Foo f
         WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :filter, '%'))
         ORDER BY
            CASE
                WHEN LOWER(f.name) = LOWER(:filter) THEN 0
                WHEN LOWER(f.name) LIKE LOWER(CONCAT(:filter, '%')) THEN 1
                WHEN LOWER(f.name) LIKE LOWER(CONCAT('%', :filter)) THEN 2
                WHEN LOWER(f.name) LIKE LOWER(CONCAT('%', :filter, '%')) THEN 3
                ELSE 4
            END
         """)
   Page<Foo> findAllWithFilter(@Param("filter") String filter, @NonNull Pageable page);

   Page<Foo> findAll(@NonNull Pageable page);

}
