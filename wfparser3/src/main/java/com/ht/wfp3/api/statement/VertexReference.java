package com.ht.wfp3.api.statement;

interface VertexReference {

  static final int INDEX_NOT_SET_VALUE = 0;

  boolean isSet();

  Integer getVertexIndex();

  boolean equals(Object object);

  public int hashCode();
}
