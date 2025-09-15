package com.abm.apps.im.web.assemblers;

import java.io.Serializable;
import java.util.List;

public interface GenericResourceAssembler<D, T> extends Serializable {
  T toModel(D document);
  List<T> toModels(List<D> domainList);
}
