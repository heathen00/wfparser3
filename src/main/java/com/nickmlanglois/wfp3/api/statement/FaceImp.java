package com.nickmlanglois.wfp3.api.statement;

import java.util.List;

class FaceImp extends StatementsUsingVertexReferenceGroupsImp implements Face {
  private static final String KEYWORD = "f";

  FaceImp(List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(KEYWORD, MINIMUM_VERTEX_REFERENCE_GROUPS, vertexReferenceGroupList);
  }

  FaceImp(Face face) {
    this(face.getVertexReferenceGroupList());
  }

  @Override
  public String toString() {
    return "FaceImp [super.toString()=" + super.toString() + "]";
  }

  @Override
  public int compareTo(Face o) {
    return compareToCommon(o);
  }
}
