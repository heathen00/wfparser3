package com.ht.wfp3.api;

public interface ReferenceNumber {
  enum Vertex {
    GEOMETRIC,
    TEXTURE,
    NORMAL,
    PARAMETER,
  }

  boolean hasValue();

  int getValue();

  ReferenceNumber.Vertex getVertex();
}
