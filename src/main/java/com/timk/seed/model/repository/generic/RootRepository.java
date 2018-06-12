package com.timk.seed.model.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface RootRepository<T, I extends Serializable> extends JpaRepository<T, I> {
}
