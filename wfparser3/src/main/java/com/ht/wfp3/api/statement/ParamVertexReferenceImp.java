package com.ht.wfp3.api.statement;

class ParamVertexReferenceImp extends VertexReferenceImp implements ParamVertexReference {

  ParamVertexReferenceImp(Integer vertexIndex) {
    super(vertexIndex);
  }

  ParamVertexReferenceImp(ParamVertexReference paramVertexReference) {
    this(paramVertexReference.getVertexIndex());
  }
}
