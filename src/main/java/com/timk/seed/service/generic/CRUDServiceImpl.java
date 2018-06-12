package com.timk.seed.service.generic;

import com.timk.seed.model.repository.generic.RootRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

public abstract class CRUDServiceImpl<T, I extends Serializable> implements CRUDService<T, I> {

  protected abstract RootRepository<T, I> getRepository();

  @Transactional
  public T create(T value) {
    return getRepository().save(value);
  }

  @Transactional(readOnly = true, propagation = SUPPORTS)
  public T read(I id) {
    return getRepository().findById(id).orElse(null);
  }

  @Transactional(readOnly = true, propagation = SUPPORTS)
  public List<T> list() {
    return getRepository().findAll();
  }

  @Transactional(readOnly = true, propagation = SUPPORTS)
  public Page<T> page(Pageable pageable) {
    return getRepository().findAll(pageable);
  }

  @Transactional
  public T update(T value) {
    return getRepository().save(value);
  }

  @Transactional
  public void delete(I id) {
    getRepository().deleteById(id);
  }
}
