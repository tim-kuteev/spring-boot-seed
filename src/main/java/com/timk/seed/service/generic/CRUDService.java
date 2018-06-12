package com.timk.seed.service.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<T, I> {

  T create(T value);

  T read(I id);

  List<T> list();

  Page<T> page(Pageable pageable);

  T update(T value);

  void delete(I id);
}
