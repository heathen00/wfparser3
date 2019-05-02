package com.ht.wfp3.api.statement;

class TexVertexReferenceImp extends VertexReferenceImp implements TexVertexReference {

  TexVertexReferenceImp(Integer vertexIndex) {
    super(vertexIndex);
  }

  TexVertexReferenceImp(TexVertexReference texVertexReference) {
    this(texVertexReference.getVertexIndex());
  }
}
