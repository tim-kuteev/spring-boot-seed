package com.timk.seed.rest.generic;

import com.timk.seed.service.mapper.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.NEVER;

@Transactional(propagation = NEVER)
public abstract class BaseController {

  @Autowired protected OrikaMapper mapper;
}
