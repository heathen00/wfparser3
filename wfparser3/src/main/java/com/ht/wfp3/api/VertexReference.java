package com.ht.wfp3.api;

public interface VertexReference {
  enum Type {
    GEOMETRIC,
    TEXTURE,
    NORMAL,
    PARAMETER,
  }

  boolean hasValue();

  int getValue();

  VertexReference.Type getVertex();
}
