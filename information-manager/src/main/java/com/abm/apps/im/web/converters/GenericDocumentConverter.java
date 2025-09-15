package com.abm.apps.im.web.converters;

import java.io.Serializable;
import java.util.List;

public interface GenericDocumentConverter<T, D> extends Serializable {
  D toDocument(T dto);
  List<D> toDocuments(List<T> dtoList);
}
