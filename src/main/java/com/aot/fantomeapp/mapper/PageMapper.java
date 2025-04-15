package com.aot.fantomeapp.mapper;

import com.aot.fantomeapp.dto.PageResultDto;
import com.aot.fantomeapp.utils.PageResult;

public class PageMapper {

   public static <T, U> PageResultDto<T> toPageResultDto(PageResult<U> page, T t){
      PageResultDto<T> res = new PageResultDto<>();
      res.setContent(t);
      res.setTotalElements(page.getTotalElements());
      res.setTotalPages(page.getTotalPages());

      return res;
   }

}
