package com.timk.seed.service.mapper;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class OrikaMapper extends ConfigurableMapper {

  private MapperFactory factory;
  private List<Converter> converters;

  public OrikaMapper() {
    super();
  }

  public OrikaMapper(boolean autoInit) {
    super(autoInit);
  }

  public OrikaMapper(Converter... converters) {
    this.converters = asList(converters);
    init();
  }

  public MapperFactory getFactory() {
    return factory;
  }

  @Override
  protected void configure(final MapperFactory factory) {
    this.factory = factory;
    if (converters != null) {
      converters.forEach(factory.getConverterFactory()::registerConverter);
    }
  }

  public <S, D> Page<D> mapAsPage(Page<S> source, Class<D> destination, Pageable pageable) {
    List<D> content = mapAsList(source.getContent(), destination);
    return new PageImpl<>(content, pageable, source.getTotalElements());
  }

  public <S, D> Page<D> mapListAsPage(List<S> source, Class<D> destination, Pageable pageable) {
    return new PageImpl<>(mapAsList(source, destination), pageable, source.size());
  }

  public <S, D> Page<D> mapListAsMaxPage(List<S> source, Class<D> destination) {
    return mapListAsPage(source, destination, PageRequest.of(0, Integer.MAX_VALUE));
  }
}
