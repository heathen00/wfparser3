package com.ht.wfp3.api.statement;

interface VertexReference {

  static final Integer INDEX_NOT_SET_VALUE = Integer.valueOf(0);

  boolean isSet();

  Integer getVertexIndex();

  boolean equals(Object object);

  public int hashCode();
}
