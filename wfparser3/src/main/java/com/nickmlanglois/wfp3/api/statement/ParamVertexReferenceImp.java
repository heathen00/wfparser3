package com.nickmlanglois.wfp3.api.statement;

class ParamVertexReferenceImp extends VertexReferenceImp implements ParamVertexReference {

  ParamVertexReferenceImp(Integer vertexIndex) {
    super(vertexIndex);
  }

  ParamVertexReferenceImp(ParamVertexReference paramVertexReference) {
    this(paramVertexReference.getVertexIndex());
  }

  @Override
  public int compareTo(ParamVertexReference o) {
    return getVertexIndex().compareTo(o.getVertexIndex());
  }
}
