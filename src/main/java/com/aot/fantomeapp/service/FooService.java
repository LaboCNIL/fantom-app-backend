package com.aot.fantomeapp.service;

import com.aot.fantomeapp.exception.FunctionalException;
import com.aot.fantomeapp.exception.TechnicalException;
import com.aot.fantomeapp.exception.rules.FunctionalRule;
import com.aot.fantomeapp.exception.rules.TechnicalRule;
import com.aot.fantomeapp.model.Foo;
import com.aot.fantomeapp.repository.FooRepository;
import com.aot.fantomeapp.utils.PageResult;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FooService {
   
   private final FooRepository fooRepository;
   
   public Foo create(Foo dto) {
      Foo foo = new Foo();
      
      if (foo.getName().contains("bar")) {
         throw new FunctionalException(
            FunctionalRule.FOO_0001
         );
      }
      foo.setName(dto.getName());

      return fooRepository.save(foo);
   }
   
   public Foo find(Long id) {
      return fooRepository.findById(id)
         .orElseThrow(() -> new TechnicalException(
            TechnicalRule.FOO_0001, 
            HttpStatus.NOT_FOUND
         ));
   }

   public PageResult<List<Foo>> findAll(Integer page, Integer limit, @Nullable String filter) {
      PageResult<List<Foo>> pageResult = new PageResult<>();
      Page<Foo> res;
      if (filter != null) {
         res = fooRepository.findAllWithFilter(filter, PageRequest.of(page, limit));
      } else {
         res = fooRepository.findAll(PageRequest.of(page, limit));
      }

      pageResult.setTotalElements(res.getTotalElements());
      pageResult.setTotalPages(res.getTotalPages());
      pageResult.setContent(res.getContent());

      return pageResult;
   }
   
   public Foo update(Foo dto) {
      Foo foo = find(dto.getId());
      
      foo.setName(dto.getName());
      foo.setBars(dto.getBars());
      foo.setAttributCamelCase(dto.getAttributCamelCase());
      
      return foo;
   }
   
   public void delete(Foo dto) {
      fooRepository.delete(dto);
   }
   
}
