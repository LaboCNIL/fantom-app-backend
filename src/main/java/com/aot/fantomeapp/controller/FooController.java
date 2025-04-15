package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.dto.FooDto;
import com.aot.fantomeapp.dto.PageResultDto;
import com.aot.fantomeapp.mapper.FooMapper;
import com.aot.fantomeapp.mapper.PageMapper;
import com.aot.fantomeapp.model.Foo;
import com.aot.fantomeapp.utils.PageResult;
import com.aot.fantomeapp.service.FooService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/foo")
public class FooController {
   
   private final FooService fooService;

   @GetMapping
   public ResponseEntity<FooDto> find(
      @RequestParam("foo_id") Long id
   ) {
      return ResponseEntity.ok(
         FooMapper.toDto(
            fooService.find(id)
         )
      );
   }

   @GetMapping("/all")
   public ResponseEntity<PageResultDto<List<FooDto>>> findAll(
      @RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit,
      @RequestParam(value = "filter", required = false) String filter
   ) {
      PageResult<List<Foo>> res = fooService.findAll(page, limit, filter);
      return ResponseEntity.ok(
         PageMapper.toPageResultDto(res, FooMapper.toDtoList(res.getContent()))
      );
   }

}
