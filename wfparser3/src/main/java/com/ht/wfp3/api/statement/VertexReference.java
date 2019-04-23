package com.ht.wfp3.api.statement;

public interface VertexReference {
  enum Type {
    GEOMETRIC, TEXTURE, NORMAL, PARAMETER,
  }

  static final int INDEX_NOT_SET_VALUE = 0;

  boolean isSet();

  Integer getVertexIndex();

  Type getVertexType();
}
