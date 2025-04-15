package com.aot.fantomeapp.utils;

import lombok.Data;

@Data
public class PageResult<T> {
   private T content;
   private long totalElements;
   private long totalPages;
}
