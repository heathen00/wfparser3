package com.ht.wfp3.api.statement;

class NormalVertexReferenceImp extends VertexReferenceImp implements NormalVertexReference {

  NormalVertexReferenceImp(Integer vertexIndex) {
    super(vertexIndex);
  }

  NormalVertexReferenceImp(NormalVertexReference normalVertexReference) {
    this(normalVertexReference.getVertexIndex());
  }
}
