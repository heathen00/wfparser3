package com.nickmlanglois.wfp3.api.statement;

class GeoVertexReferenceImp extends VertexReferenceImp implements GeoVertexReference {

  GeoVertexReferenceImp(Integer vertexIndex) {
    super(vertexIndex);
  }

  GeoVertexReferenceImp(GeoVertexReference geoVertexReference) {
    this(geoVertexReference.getVertexIndex());
  }

  @Override
  public int compareTo(GeoVertexReference o) {
    return getVertexIndex().compareTo(o.getVertexIndex());
  }
}
