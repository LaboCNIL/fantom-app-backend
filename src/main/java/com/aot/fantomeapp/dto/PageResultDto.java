package com.aot.fantomeapp.dto;

import lombok.Data;

@Data
public class PageResultDto<T> {
   private T content;
   private long totalElements;
   private long totalPages;
}
